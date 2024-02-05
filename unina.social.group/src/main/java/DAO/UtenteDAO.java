package DAO;

import java.sql.*;

import model.Utente;
import util.PgDatabaseConnector;

public class UtenteDAO{
	
	private Connection connection;
	private PgDatabaseConnector dbConnector = new PgDatabaseConnector();
	
	public UtenteDAO() throws SQLException {
		connection = PgDatabaseConnector.getConnection();
	}

	public void salvaUtente(Utente utente) throws Exception {
 
        if (connection != null) 
        {
        	try 
        	{
                String cmd = "INSERT INTO Utente (email, username, password, nome,"
                		   + " cognome, dataNascita, dataIscrizione, tipo) "
             		       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                
        		PreparedStatement pstmt = connection.prepareStatement(cmd);
                pstmt.setString(1, utente.getEmail());
                pstmt.setString(2, utente.getUsername());
                pstmt.setString(3, utente.getPassword());
                pstmt.setString(4, utente.getNome());
                pstmt.setString(5, utente.getCognome());
                pstmt.setDate(6, Date.valueOf(utente.getDataNascita()));  
                pstmt.setDate(7, Date.valueOf(utente.getDataIscrizione()));
                pstmt.setString(8, utente.getTipo());
                
                pstmt.executeUpdate();
                
                System.out.println("L'inserimento è andato a buon fine.\n");
                
                dbConnector.closeResources(pstmt, null, connection);
        	}
            catch (SQLException e) 
            {
            	System.out.println("L'inserimento è fallito.\n" + e);
                dbConnector.closeResources(null, null, connection);
            }
        }
    }
	
	
    public void aggiornaUtente(Utente utente) throws Exception {

        if (connection != null) 
        {
            try 
            {
                String cmd = "UPDATE Utente SET email=?, username=?, password=?, nome=?, cognome=?, "
             		       + "dataNascita=?, dataIscrizione=?, tipo=? WHERE username=?";
                
                PreparedStatement pstmt = connection.prepareStatement(cmd);
                pstmt.setString(1, utente.getEmail());
                pstmt.setString(2, utente.getUsername());
                pstmt.setString(3, utente.getPassword());
                pstmt.setString(4, utente.getNome());
                pstmt.setString(5, utente.getCognome());
                pstmt.setDate(6, Date.valueOf(utente.getDataNascita()));  
                pstmt.setDate(7, Date.valueOf(utente.getDataIscrizione()));
                pstmt.setString(8, utente.getTipo());
                pstmt.setString(9, utente.getUsername());

                pstmt.executeUpdate();
                
                System.out.println("L'aggiornamento è andato a buon fine.\n");

                dbConnector.closeResources(pstmt, null, connection);
            } 
            catch (SQLException e) 
            {
            	System.out.println("Non è stato possibile aggiornare i dati dell'utente\n" + e);
                dbConnector.closeResources(null, null, connection);
            }
        }
    }
    
    
    public void aggiornaColonnaUtente(Utente utente, String nomeColonna, Object nuovoValore) throws Exception {

        if (connection != null) 
        {
            try 
            {
            	String cmd = "UPDATE Utente SET " + nomeColonna + "=? WHERE username=?";
            	
                PreparedStatement pstmt = connection.prepareStatement(cmd);
                pstmt.setObject(1, nuovoValore);
                pstmt.setString(2, utente.getUsername());

                pstmt.executeUpdate();
                
                System.out.println("L'aggiornamento è andato a buon fine.\n");

                dbConnector.closeResources(pstmt, null, connection);
            } 
            catch (SQLException e) 
            {
            	System.out.println("Non è stato possibile aggiornare la colonna " + nomeColonna + "\n" + e);
            	dbConnector.closeResources(null, null, connection);
            } 
        }
    }
    
    
    
    public void eliminaUtente(Utente utente) throws Exception {
    	
        if (connection != null) 
        {
            try 
            {
            	String cmd = "DELETE FROM Utente WHERE username=?";
            	
                PreparedStatement pstmt = connection.prepareStatement(cmd);
                pstmt.setString(1, utente.getUsername());

                pstmt.executeUpdate();
                
                System.out.println("L'eliminazione è andata a buon fine.\n");
                
                dbConnector.closeResources(pstmt, null, connection);                
            } 
            catch (SQLException e) 
            {
            	System.out.println("Non è stato possibile eliminare l'utente\n" + e);
            	dbConnector.closeResources(null, null, connection);
            } 
        }
    }
   
        
        public Utente getByUsername(String username) throws Exception {
        	
        	Utente u = null;
        	
        	if(connection != null)
        	{
        		try
        		{
        			String cmd = "SELECT * FROM Utente WHERE username = ?";
        			
        			PreparedStatement pstmt = connection.prepareStatement(cmd);
        			pstmt.setString(1, username);
        			ResultSet rs = pstmt.executeQuery();
        			
        			if (rs.next()) 
                    {	
                    	u = new Utente(rs.getString("email"),    
                    				   rs.getString("username"), 
                    				   rs.getString("password"), 
                    				   rs.getString("nome"), 
                    			       rs.getString("cognome"),  
                    			       rs.getDate("dataNascita").toLocalDate(), 
                    			       rs.getDate("dataIscrizione").toLocalDate(), 
                    			       rs.getString("tipo"));


                        System.out.println("email: " + u.getEmail() +
                                           "\nusername: " + u.getUsername() +
                                           "\npassword: " + u.getPassword() +
                                           "\nnome: " + u.getNome() +
    		                               "\ncognome: " + u.getCognome() +
    		                               "\ndataNascita: " + u.getDataNascita() +
    		                               "\ndataIscrizione: " + u.getDataIscrizione() +
    		                               "\ntipo: " + u.getTipo());
                        
                        dbConnector.closeResources(pstmt, rs, connection);
                    } 
                    else 
                    {
                    	System.out.println("Non esiste alcun utente con username = " + username + "\n");
                    }
        			
        		}
        		catch (SQLException e) 
        		{
        			System.out.println("Non è stato possibile ottenere i dati dell'utente\n" + e);
        			return u;
        		}
        	}
        	
        	return u;
        }
    
}