package DAO;

import java.sql.*;
import java.time.LocalDateTime;

import model.Gruppo;
import util.PgDatabaseConnector;

public class GruppoDAO{
	
	private Connection connection;
	private PgDatabaseConnector dbConnector = new PgDatabaseConnector();
	
	public GruppoDAO() throws SQLException {
		connection = PgDatabaseConnector.getConnection();
	}

	public boolean salvaGruppo(Gruppo gruppo) throws Exception {
 
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
	
	public boolean aggiornaGruppo(Gruppo gruppo, String idGruppo) {
			
		boolean esito = false;

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
	
	public boolean aggiornaColonnaGruppo(String idGruppo, String nomeColonna, Object nuovoValore) {
		
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
            else {
            	esito = true;
            }
            
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
	
	public Gruppo getByIdGruppo(String idGruppo) {
		
		Gruppo gruppo = null;
		
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
			
			//creaGruppo
		}
		catch (SQLException e) 
		{
			
		}
		return gruppo;
	}

	
}
