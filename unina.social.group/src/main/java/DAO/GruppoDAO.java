package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Tema;
import model.Gruppo;
import util.PgDatabaseConnector;

public class GruppoDAO{

	private Connection connection;
	private PgDatabaseConnector dbConnector;
	private UtenteDAO utenteDAO;
	
	public GruppoDAO() {
		dbConnector = new PgDatabaseConnector();
		utenteDAO = new UtenteDAO();
	}

	public boolean insert(Gruppo group) {
		connection = PgDatabaseConnector.getConnection();
        if (connection == null) {
        	return false;
        }
        
        PreparedStatement pstmt = null;
        
    	try {
            String cmd = "INSERT INTO Gruppo (nome, dataOraCreazione, "
            		   + "descrizione, tema, idOwner) "
         		       + "VALUES (?, ?, ?, ?, ?) RETURNING idGruppo";
            
    		pstmt = connection.prepareStatement(cmd);
    		
            pstmt.setString(1, group.getNome());
            pstmt.setTimestamp(2, Timestamp.valueOf(group.getDataOraCreazione()));
            pstmt.setString(3, group.getDescrizione());
            pstmt.setString(4, group.getTema().getTema());
            pstmt.setInt(5, Integer.valueOf(group.getOwner().getIdUtente()));
            
            return pstmt.executeUpdate() > 0;
    	} catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	dbConnector.closeResources(null, pstmt, connection);
        }
    	
    	return false;
    }
	
	public Gruppo getById(String id) {
		Gruppo group = null;
		
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
	
	public List<Gruppo> getByName(String name) {
		List<Gruppo> searchResults = new ArrayList<>();
		
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
	
	public List<Gruppo> getByTopic(String topic) {
		List<Gruppo> searchResults = new ArrayList<>();
		
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
	
	public int geMemberCount(String idGruppo) {
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
            pstmt.setInt(1, Integer.parseInt(idGruppo));
            
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
	
	public int getPostCount(String idGruppo) {
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
            pstmt.setInt(1, Integer.parseInt(idGruppo));
            
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
	
	public List<Gruppo> getSuggestedGroups(String idUtente) {
		List<Gruppo> groups = new ArrayList<>();
		
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
						"WHERE idUtente = ?)" +
					"AND idGruppo NOT IN (" + 
						"SELECT idGruppo " +
						"FROM MembroGruppo " +
						"WHERE idUtente = ?)";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(idUtente));
            pstmt.setInt(2, Integer.parseInt(idUtente));
            
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

	public List<Gruppo> getGroupsOwned(String idUtente) {
		List<Gruppo> groups = new ArrayList<>();
		
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
					"WHERE idOwner = ? " +
					"ORDER BY nome";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(idUtente));
            
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
	
	public Gruppo createGroup(ResultSet rs) {
		Gruppo group = null;
		try {
			group = new Gruppo(
					String.valueOf(rs.getInt("idGruppo")),
					rs.getString("nome"),
					rs.getTimestamp("dataOraCreazione").toLocalDateTime(),
					rs.getString("descrizione"),                
					new Tema(rs.getString("tema")),                
					utenteDAO.getById(String.valueOf(rs.getInt("idOwner")))
			);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;
	}

}
