package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Group;

public class GroupDAO{

	private Connection connection;
	private PgDatabaseConnector dbConnector;
	
	public GroupDAO() {
		dbConnector = new PgDatabaseConnector();
	}

	public boolean insert(Group group) {
		connection = PgDatabaseConnector.getConnection();
        if (connection == null) {
        	return false;
        }
        
        PreparedStatement pstmt = null;
        
    	try {
            String cmd = "INSERT INTO Gruppo (nome, dataOraCreazione, "
            		   + "descrizione, tema, usernameOwner) "
         		       + "VALUES (?, ?, ?, ?, ?) RETURNING idGruppo";
            
    		pstmt = connection.prepareStatement(cmd);
    		
            pstmt.setString(1, group.getName());
            pstmt.setTimestamp(2, Timestamp.valueOf(group.getCreatedAt()));
            pstmt.setString(3, group.getDescription());
            pstmt.setString(4, group.getTopic());
            pstmt.setInt(5, Integer.valueOf(group.getOwner().getUsername()));
            
            return pstmt.executeUpdate() > 0;
    	} catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	dbConnector.closeResources(null, pstmt, connection);
        }
    	
    	return false;
    }

	public Group getById(String id) {
		Group group = null;
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return group;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
			String cmd = 
					"SELECT * " +
					"FROM Gruppo " + 
					"WHERE idGruppo = ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(id));
            
			rs = pstmt.executeQuery();
			
			rs.next();
            group = createGroup(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return group;
	}
	
	public List<Group> getByName(String name) {
		List<Group> searchResults = new ArrayList<>();
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return searchResults;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
			String cmd = 
					"SELECT * " +
					"FROM Gruppo " + 
					"WHERE nome ILIKE ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, "%" + name + "%");
            
			rs = pstmt.executeQuery();
			
            while (rs.next()) {
            	searchResults.add(createGroup(rs));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return searchResults;
	}
	
	public List<Group> getByTopic(String topic) {
		List<Group> searchResults = new ArrayList<>();
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return searchResults;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		try	{
			String cmd = 
					"SELECT * " + 
					"FROM Gruppo " + 
					"WHERE tema ILIKE ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, "%" + topic + "%");
            
			rs = pstmt.executeQuery();
			
            while (rs.next()) {
            	searchResults.add(createGroup(rs));
            }
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return searchResults;
	}
	
	public int getMemberCount(String groupId) {
		int memberCount = 0;
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return memberCount;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT COUNT(*) "+ 
					"FROM Gruppo G JOIN MembroGruppo MG " +
					"ON G.idGruppo = MG.idGruppo " +
					"WHERE G.idGruppo = ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(groupId));
            
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return memberCount;
	}
	
	public int getPostCount(String groupId) {
		int postCount = 0;
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return postCount;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT COUNT(*) "+ 
					"FROM Gruppo G JOIN Post P " +
					"ON G.idGruppo = P.idGruppo " +
					"WHERE G.idGruppo = ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(groupId));
            
			rs = pstmt.executeQuery();

			if (rs.next()) {
				postCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return postCount;
	}
	
	public List<Group> getSuggestedGroups(String username) {
		List<Group> groups = new ArrayList<>();
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return groups;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT * " +
					"FROM Gruppo " +
					"WHERE tema IN (" +
						"SELECT G.tema " +
						"FROM MembroGruppo MG JOIN Gruppo G " +
						"ON MG.idGruppo = G.idGruppo " +
						"WHERE usernameUtente = ?)" +
					"AND idGruppo NOT IN (" + 
						"SELECT idGruppo " +
						"FROM MembroGruppo " +
						"WHERE usernameUtente = ?)" +
					"AND idGruppo NOT IN (" + 
						"SELECT idGruppo " + 
						"FROM RichiestaDiAccesso " + 
						"WHERE usernameMittente = ?)";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, username);
            pstmt.setString(2, username);
            pstmt.setString(3, username);
            
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groups.add(createGroup(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return groups;
	}

	public List<Group> getGroupsOwned(String username) {
		List<Group> groups = new ArrayList<>();
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return groups;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT * " +
					"FROM Gruppo " +
					"WHERE usernameOwner = ? " +
					"ORDER BY nome";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, username);
            
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groups.add(createGroup(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return groups;
	}
	
	public boolean isMember(String groupId, String username) {		
		connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return false;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT COUNT(*) "+ 
					"FROM MembroGruppo " +
					"WHERE idGruppo = ? " + 
					"AND usernameUtente = ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(groupId));
            pstmt.setString(2, username);
            
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1)>0;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return false;
	}
	
	public List<String> getUserGroupIds(String username) {
		List<String> groupsIds = new ArrayList<>();
		
		connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return groupsIds;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT idGruppo "+ 
					"FROM MembroGruppo " +
					"WHERE usernameUtente = ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, username);
            
			rs = pstmt.executeQuery();

            while (rs.next()) {
            	groupsIds.add(rs.getString("idGruppo"));
            }
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return groupsIds;
	}
	
	public Group createGroup(ResultSet rs) {
		Group group = null;
		UserDAO userDAO = new UserDAO();
		
		try {
			group = new Group(
					String.valueOf(rs.getInt("idGruppo")),
					userDAO.getByUsername(rs.getString("usernameOwner")),
					rs.getString("nome"),
					rs.getString("tema"),    
					rs.getString("descrizione"),                
					rs.getTimestamp("dataOraCreazione").toLocalDateTime()
			);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;
	}

}
