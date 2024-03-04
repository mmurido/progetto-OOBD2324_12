package DAO;

import java.sql.*;
import java.time.LocalDateTime;

import model.Gruppo;
import util.PgDatabaseConnector;

public class GruppoDAO{
	
	private Connection connection;
	private PgDatabaseConnector dbConnector = new PgDatabaseConnector();
	
<<<<<<< Updated upstream
	public GruppoDAO() throws SQLException {
		connection = PgDatabaseConnector.getConnection();
	}

	public boolean salvaGruppo(Gruppo gruppo) throws Exception {
 
=======
	public GruppoDAO() {
		dbConnector = new PgDatabaseConnector();
		utenteDAO = new UtenteDAO();
	}

	public boolean insert(Gruppo group) {
		connection = PgDatabaseConnector.getConnection();
>>>>>>> Stashed changes
        if (connection == null) {
        	return false;
        }
        
    	try 
    	{
            String cmd = "INSERT INTO Gruppo (nome, dataOraCreazione, "
            		   + "descrizione, tema, idOwner) "
         		       + "VALUES (?, ?, ?, ?, ?) RETURNING idGruppo";
            
    		PreparedStatement pstmt = connection.prepareStatement(cmd);
    		
            pstmt.setString    (1, gruppo.getNome());
            pstmt.setTimestamp (2, Timestamp.valueOf(gruppo.getDataOraCreazione()));
            pstmt.setString    (3, gruppo.getDescrizione());
            pstmt.setString    (4, gruppo.getTema().getTema());
            pstmt.setInt       (5, Integer.valueOf(gruppo.getOwner().getIdUtente()));
            
            pstmt.executeUpdate();            
            System.out.println("Inserimento riuscito.\n");            
            dbConnector.closeResources(pstmt, null, connection);
    	}
        catch (SQLException e) 
        {
        	System.out.println("Inserimento fallito.\n" + e);
            dbConnector.closeResources(null, null, connection);
            return false;
        }     
    	
    	return true;
    }
	
<<<<<<< Updated upstream
	public boolean aggiornaGruppo(Gruppo gruppo, String idGruppo) {
			
		boolean esito = false;

=======
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
	
	public Gruppo getById(String id) {
		Gruppo group = null;
		
        connection = PgDatabaseConnector.getConnection();
>>>>>>> Stashed changes
		if (connection == null) {
			return esito;
		}
		
		try
		{
			String cmd = "UPDATE Gruppo SET idGruppo=?, nome=?, dataOraCreazione=?"
					   + ", descrizione=?, tema=? WHERE idGruppo=?";
			
			PreparedStatement pstmt = connection.prepareStatement(cmd);
			
			pstmt.setInt       (1, Integer.valueOf(gruppo.getIdGruppo()));
			pstmt.setString    (2, gruppo.getNome());
			pstmt.setTimestamp (3, Timestamp.valueOf(gruppo.getDataOraCreazione()));
			pstmt.setString    (4, gruppo.getDescrizione());
			pstmt.setString    (5, gruppo.getTema().getTema());
			
			int affectedRows = pstmt.executeUpdate();
			
			if (affectedRows == 0) {
				esito = false;
			}
			else {
				esito = true;
			}
			
			System.out.println("Righe aggiornate: " + affectedRows + "\n");					
			dbConnector.closeResources(pstmt, null, connection);			
		}
		catch (SQLException e)
		{
			System.out.println("Aggiornamento fallito.\n" + e);
		}
		
		return esito;
	}
	
<<<<<<< Updated upstream
	public boolean aggiornaColonnaGruppo(String idGruppo, String nomeColonna, Object nuovoValore) {
=======
	public List<Gruppo> getByName(String name) {
		List<Gruppo> searchResults = new ArrayList<>();
>>>>>>> Stashed changes
		
		boolean esito = false;

		if (connection == null) {
			return esito;
		}
		
		try
		{
			String cmd = "UPDATE Gruppo SET " + nomeColonna + "=? WHERE idGruppo=?";
			
			PreparedStatement pstmt = connection.prepareStatement(cmd);
			
			pstmt.setObject    (1, nuovoValore);
			pstmt.setInt       (2, Integer.valueOf(idGruppo));
			
			int affectedRows = pstmt.executeUpdate();
			
			if (affectedRows == 0) {
				esito = false;
			}
			else {
				esito = true;
			}
			
			System.out.println("Righe aggiornate: " + affectedRows + "\n");
			dbConnector.closeResources(pstmt, null, connection);			
		}
		catch (SQLException e)
		{
			System.out.println("Aggiornamento fallito.\n" + e);
		}
		
		return esito;
	}
	
	public boolean eliminaGruppo(Gruppo gruppo) throws Exception {
    	
		boolean esito = false;
		
    	if (connection == null) {
    		return esito;
    	}

        try 
        {
        	String cmd = "DELETE FROM Gruppo WHERE idGruppo=?";        	
            PreparedStatement pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, gruppo.getIdGruppo());
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
            	esito = false;
            }
<<<<<<< Updated upstream
            else {
            	esito = true;
            }
=======
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
>>>>>>> Stashed changes
            
            System.out.println("Righe eliminate: " + affectedRows + "\n");            
            dbConnector.closeResources(pstmt, null, connection);                
        } 
        catch (SQLException e) 
        {
        	System.out.println("Eliminazione fallita.\n" + e);
        	dbConnector.closeResources(null, null, connection);
        }    
        
        return esito;
    }
	
<<<<<<< Updated upstream
	public Gruppo getByIdGruppo(String idGruppo) {
		
		Gruppo gruppo = null;
=======
	public int getPostCount(String idGruppo) {
		int postCount = 0;
>>>>>>> Stashed changes
		
		if (connection == null) {
			return gruppo;
		}
		
		try 
		{
			String cmd = "SELECT * FROM Gruppo WHERE idGruppo=?";
			PreparedStatement pstmt = connection.prepareStatement(cmd);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				System.out.println("Non esiste alcun gruppo con id = " + idGruppo + ".\n");
				dbConnector.closeResources(pstmt, rs, connection);
				return gruppo;
			}
<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
			
			//creaGruppo
		}
		catch (SQLException e) 
		{
			
		}
		return gruppo;
	}

	
<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
}
