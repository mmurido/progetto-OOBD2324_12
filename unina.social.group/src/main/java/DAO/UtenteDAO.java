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

	public boolean salvaUtente(Utente utente) throws Exception {
 
        if (connection == null) {
        	return false;
        }
        
    	try 
    	{
            String cmd = "INSERT INTO Utente (email, username, password, nome,"
            		   + " cognome, dataNascita, dataIscrizione, tipo) "
         		       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING idUtente";
            
    		PreparedStatement pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, utente.getEmail());
            pstmt.setString(2, utente.getUsername());
            pstmt.setString(3, utente.getPassword());
            pstmt.setString(4, utente.getNome());
            pstmt.setString(5, utente.getCognome());
            pstmt.setDate(6, Date.valueOf(utente.getDataNascita()));  
            pstmt.setDate(7, Date.valueOf(utente.getDataIscrizione()));
            pstmt.setString(8, utente.getTipo());
            
            ResultSet rs = pstmt.executeQuery(); 
            if (rs.next()) {
            	utente.setIdUtente(String.valueOf(rs.getInt("idUtente")));
                System.out.println("L'inserimento è andato a buon fine.\n");            
                dbConnector.closeResources(pstmt, null, connection);
                return true;
            }

    	}
        catch (SQLException e) 
        {
        	System.out.println("L'inserimento è fallito.\n" + e);
            dbConnector.closeResources(null, null, connection);
            return false;
        }     
    	
    	return false;
    }
	
	
    public boolean aggiornaUtente(Utente utente, String idUtente) throws Exception {

        if (connection == null) {
        	return false;
        }
        
        try 
        {
            String cmd = "UPDATE Utente SET idUtente=?, email=?, username=?, password=?, nome=?, cognome=?, "
         		       + "dataNascita=?, dataIscrizione=?, tipo=? WHERE idUtente=?";
            
            PreparedStatement pstmt = connection.prepareStatement(cmd);
            pstmt.setString (1, utente.getIdUtente());
            pstmt.setString (2, utente.getEmail());
            pstmt.setString (3, utente.getUsername());
            pstmt.setString (4, utente.getPassword());
            pstmt.setString (5, utente.getNome());
            pstmt.setString (6, utente.getCognome());
            pstmt.setDate   (7, Date.valueOf(utente.getDataNascita()));  
            pstmt.setDate   (8, Date.valueOf(utente.getDataIscrizione()));
            pstmt.setString (9, utente.getTipo());
            pstmt.setString (10, idUtente);

            pstmt.executeUpdate();            
            System.out.println("L'aggiornamento è andato a buon fine.\n");
            dbConnector.closeResources(pstmt, null, connection);
        } 
        catch (SQLException e) 
        {
        	System.out.println("Non è stato possibile aggiornare i dati dell'utente\n" + e);
            dbConnector.closeResources(null, null, connection);
            return false;
        }        
        
        return true;
    }
    
    
    public boolean aggiornaColonnaUtente(String idUtente, String nomeColonna, Object nuovoValore) throws Exception {

        if (connection == null) {
        	return false;
        }
        
        try 
        {
        	String cmd = "UPDATE Utente SET " + nomeColonna + "=? WHERE idUtente=?";        	
            PreparedStatement pstmt = connection.prepareStatement(cmd);
            pstmt.setObject(1, nuovoValore);
            pstmt.setString(2, idUtente);
            pstmt.executeUpdate();            
            System.out.println("L'aggiornamento è andato a buon fine.\n");
            dbConnector.closeResources(pstmt, null, connection);
        } 
        catch (SQLException e) 
        {
        	System.out.println("Non è stato possibile aggiornare la colonna " + nomeColonna + "\n" + e);
        	dbConnector.closeResources(null, null, connection);
        	return false;
        } 
        
        return true;
    }
    
    public boolean eliminaUtente(Utente utente) throws Exception {
    	
    	if (connection == null) {
    		return false;
    	}

        try 
        {
        	String cmd = "DELETE FROM Utente WHERE idUtente=?";        	
            PreparedStatement pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, utente.getIdUtente());
            pstmt.executeUpdate();            
            System.out.println("L'eliminazione è andata a buon fine.\n");            
            dbConnector.closeResources(pstmt, null, connection);                
        } 
        catch (SQLException e) 
        {
        	System.out.println("Non è stato possibile eliminare l'utente\n" + e);
        	dbConnector.closeResources(null, null, connection);
        	return false;
        }    
        
        return true;
    }
     
    private Utente creaUtente(Utente u, ResultSet rs) throws SQLException {
    	u = new Utente(rs.getString("email"),    
				   	   rs.getString("username"), 
				       rs.getString("password"), 
				       rs.getString("nome"), 
			           rs.getString("cognome"),  
			           rs.getDate("dataNascita").toLocalDate(), 
			           rs.getDate("dataIscrizione").toLocalDate(), 
			           rs.getString("tipo"));
    	return u;
    }
    
    private void stampaUtente(Utente u) {
        System.out.println("email: " + u.getEmail() +
                		   "\nusername: " + u.getUsername() +
                           "\npassword: " + u.getPassword() +
 		                   "\nnome: " + u.getNome() +
		                   "\ncognome: " + u.getCognome() +
		                   "\ndataNascita: " + u.getDataNascita() +
		                   "\ndataIscrizione: " + u.getDataIscrizione() +
		                   "\ntipo: " + u.getTipo());
    }
    
    public Utente getByIdUtente(String idUtente) throws Exception {
    	
    	Utente u = null;
    	
    	if (connection == null) {
    		return u;
    	}

		try
		{
			String cmd = "SELECT * FROM Utente WHERE idUtente = ?";
			
			PreparedStatement pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, idUtente);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				System.out.println("Non esiste alcun utente con id = " + idUtente + "\n");
				dbConnector.closeResources(pstmt, rs, connection);
				return u;
			}

			this.creaUtente(u, rs);
			this.stampaUtente(u);      
            dbConnector.closeResources(pstmt, rs, connection);
		}
		catch (SQLException e) 
		{
			System.out.println("Non è stato possibile ottenere i dati dell'utente\n" + e);
		}
    	
    	return u;
    }
    
    public Utente getByUsername(String username) throws Exception {
    	
    	Utente u = null;
    	
    	if (connection == null) {
    		return u;
    	}

		try
		{
			String cmd = "SELECT * FROM Utente WHERE username = ?";
			
			PreparedStatement pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				System.out.println("Non esiste alcun utente con username = " + username + "\n");
				dbConnector.closeResources(pstmt, rs, connection);
				return u;
			}

			this.creaUtente(u, rs);
			this.stampaUtente(u);      
            dbConnector.closeResources(pstmt, rs, connection);
		}
		catch (SQLException e) 
		{
			System.out.println("Non è stato possibile ottenere i dati dell'utente\n" + e);
		}
    	
    	return u;
    }
    
    public Utente getByEmail(String email) throws Exception {
    	
    	Utente u = null;
    	
    	if (connection == null) {
    		return u;
    	}

		try
		{
			String cmd = "SELECT * FROM Utente WHERE email = ?";
			
			PreparedStatement pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				System.out.println("Non esiste alcun utente con email = " + email + "\n");
				dbConnector.closeResources(pstmt, rs, connection);
				return u;
			}

			this.creaUtente(u, rs);
			this.stampaUtente(u);      
            dbConnector.closeResources(pstmt, rs, connection);
		}
		catch (SQLException e) 
		{
			System.out.println("Non è stato possibile ottenere i dati dell'utente\n" + e);
		}
    	
    	return u;
    }
    
    
    
    public boolean checkIfCredentialsAreCorrect(String username, String password) {
    	
    	if (connection == null) {
    		return false;
    	}

		try
		{
			String cmd = "SELECT * FROM Utente WHERE username = ? AND password=?";
			
			PreparedStatement pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				System.out.println("Non esiste alcun utente con queste credenziali.\n");
				dbConnector.closeResources(pstmt, rs, connection);
				return false;
			}

			dbConnector.closeResources(pstmt, rs, connection);
		}
		catch (SQLException e) 
		{
			System.out.println("Non è stato possibile ottenere i dati dell'utente\n" + e);
		}
		
		return true;
    }

}