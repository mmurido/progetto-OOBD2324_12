package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Gruppo;
import model.Tema;
import util.PgDatabaseConnector;

public class GruppoDAO{
	
	private Connection connection;
	private PgDatabaseConnector dbConnector;
	private UtenteDAO utenteDAO;
	
	public GruppoDAO() throws SQLException {
		dbConnector = new PgDatabaseConnector();
		utenteDAO = new UtenteDAO();
	}

	public boolean insert(Gruppo group) throws Exception {
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
	
//	public boolean update(Gruppo gruppo, String idGruppo) {
//			
//		boolean esito = false;
//
//		if (connection == null) {
//			return esito;
//		}
//		
//		try
//		{
//			String cmd = "UPDATE Gruppo SET idGruppo=?, nome=?, dataOraCreazione=?"
//					   + ", descrizione=?, tema=? WHERE idGruppo=?";
//			
//			PreparedStatement pstmt = connection.prepareStatement(cmd);
//			
//			pstmt.setInt       (1, Integer.valueOf(gruppo.getIdGruppo()));
//			pstmt.setString    (2, gruppo.getNome());
//			pstmt.setTimestamp (3, Timestamp.valueOf(gruppo.getDataOraCreazione()));
//			pstmt.setString    (4, gruppo.getDescrizione());
//			pstmt.setString    (5, gruppo.getTema().getTema());
//			
//			int affectedRows = pstmt.executeUpdate();
//			
//			if (affectedRows == 0) {
//				esito = false;
//			}
//			else {
//				esito = true;
//			}
//			
//			System.out.println("Righe aggiornate: " + affectedRows + "\n");					
//			dbConnector.closeResources(pstmt, null, connection);			
//		}
//		catch (SQLException e)
//		{
//			System.out.println("Aggiornamento fallito.\n" + e);
//		}
//		
//		return esito;
//	}
//	
//	public boolean aggiornaColonnaGruppo(String idGruppo, String nomeColonna, Object nuovoValore) {
//		
//		boolean esito = false;
//
//		if (connection == null) {
//			return esito;
//		}
//		
//		try
//		{
//			String cmd = "UPDATE Gruppo SET " + nomeColonna + "=? WHERE idGruppo=?";
//			
//			PreparedStatement pstmt = connection.prepareStatement(cmd);
//			
//			pstmt.setObject    (1, nuovoValore);
//			pstmt.setInt       (2, Integer.valueOf(idGruppo));
//			
//			int affectedRows = pstmt.executeUpdate();
//			
//			if (affectedRows == 0) {
//				esito = false;
//			}
//			else {
//				esito = true;
//			}
//			
//			System.out.println("Righe aggiornate: " + affectedRows + "\n");
//			dbConnector.closeResources(pstmt, null, connection);			
//		}
//		catch (SQLException e)
//		{
//			System.out.println("Aggiornamento fallito.\n" + e);
//		}
//		
//		return esito;
//	}
//	
//	public boolean eliminaGruppo(Gruppo gruppo) throws Exception {
//    	
//		boolean esito = false;
//		
//    	if (connection == null) {
//    		return esito;
//    	}
//
//        try 
//        {
//        	String cmd = "DELETE FROM Gruppo WHERE idGruppo=?";        	
//            PreparedStatement pstmt = connection.prepareStatement(cmd);
//            pstmt.setString(1, gruppo.getIdGruppo());
//            int affectedRows = pstmt.executeUpdate();
//            
//            if (affectedRows == 0) {
//            	esito = false;
//            }
//            else {
//            	esito = true;
//            }
//            
//            System.out.println("Righe eliminate: " + affectedRows + "\n");            
//            dbConnector.closeResources(pstmt, null, connection);                
//        } 
//        catch (SQLException e) 
//        {
//        	System.out.println("Eliminazione fallita.\n" + e);
//        	dbConnector.closeResources(null, null, connection);
//        }    
//        
//        return esito;
//    }
//	
//	public Gruppo getByIdGruppo(String idGruppo) {
//		
//		Gruppo gruppo = null;
//		
//		if (connection == null) {
//			return gruppo;
//		}
//		
//		try 
//		{
//			String cmd = "SELECT * FROM Gruppo WHERE idGruppo=?";
//			PreparedStatement pstmt = connection.prepareStatement(cmd);
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (!rs.next()) {
//				System.out.println("Non esiste alcun gruppo con id = " + idGruppo + ".\n");
//				dbConnector.closeResources(pstmt, rs, connection);
//				return gruppo;
//			}
//			
//			//creaGruppo
//		}
//		catch (SQLException e) 
//		{
//			
//		}
//		return gruppo;
//	}
	
	public Gruppo getById(String id) throws Exception {
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
	
	public List<Gruppo> getByName(String name) throws Exception {
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
	
	public List<Gruppo> getByTopic(String topic) throws Exception {
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
	
	public int geMemberCount(String idGruppo) throws SQLException {
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
	
	public int getPostCount(String idGruppo) throws SQLException {
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
	
	public List<Gruppo> getSuggestedGroups(String idUtente) throws Exception {
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
	
	public Gruppo createGroup(ResultSet rs) throws Exception {
		return new Gruppo(
				String.valueOf(rs.getInt("idGruppo")),
				rs.getString("nome"),
				rs.getTimestamp("dataOraCreazione").toLocalDateTime(),
				rs.getString("descrizione"),                
				new Tema(rs.getString("tema")),                
				utenteDAO.getById(String.valueOf(rs.getInt("idOwner")))
		);
	}
}
