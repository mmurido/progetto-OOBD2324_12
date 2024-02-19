package DAO;

import java.sql.*;

import model.Utente;
import util.PgDatabaseConnector;

public class UtenteDAO{
	
	private Connection connection;
	private PgDatabaseConnector dbConnector;
	
	public UtenteDAO() throws SQLException {
		dbConnector = new PgDatabaseConnector();
	}

	public boolean insert(Utente utente) throws Exception {
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
        	return false;
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
    	try {
            String cmd = "INSERT INTO Utente (email, username, password, nome,"
            		   + " cognome, dataNascita, dataIscrizione, tipo) "
         		       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING idUtente";
            
    		pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, utente.getEmail());
            pstmt.setString(2, utente.getUsername());
            pstmt.setString(3, utente.getPassword());
            pstmt.setString(4, utente.getNome());
            pstmt.setString(5, utente.getCognome());
            pstmt.setDate(6, Date.valueOf(utente.getDataNascita()));  
            pstmt.setDate(7, Date.valueOf(utente.getDataIscrizione()));
            pstmt.setString(8, utente.getTipo());
            
            rs = pstmt.executeQuery(); 
            
            if (rs.next()) {
            	utente.setIdUtente(String.valueOf(rs.getInt("idUtente")));
                return true;
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
			dbConnector.closeResources(rs, pstmt, connection);;
    	}
    	
    	return false;
    }
	
	
//    public boolean aggiornaUtente(Utente utente, String idUtente) throws Exception {
//
//        if (connection == null) {
//        	return false;
//        }
//        
//        try 
//        {
//            String cmd = "UPDATE Utente SET idUtente=?, email=?, username=?, password=?, nome=?, cognome=?, "
//         		       + "dataNascita=?, dataIscrizione=?, tipo=? WHERE idUtente=?";
//            
//            PreparedStatement pstmt = connection.prepareStatement(cmd);
//            pstmt.setString (1, utente.getIdUtente());
//            pstmt.setString (2, utente.getEmail());
//            pstmt.setString (3, utente.getUsername());
//            pstmt.setString (4, utente.getPassword());
//            pstmt.setString (5, utente.getNome());
//            pstmt.setString (6, utente.getCognome());
//            pstmt.setDate   (7, Date.valueOf(utente.getDataNascita()));  
//            pstmt.setDate   (8, Date.valueOf(utente.getDataIscrizione()));
//            pstmt.setString (9, utente.getTipo());
//            pstmt.setString (10, idUtente);
//
//            pstmt.executeUpdate();            
//            System.out.println("L'aggiornamento è andato a buon fine.\n");
//            dbConnector.closeResources(pstmt, null, connection);
//        } 
//        catch (SQLException e) 
//        {
//        	System.out.println("Non è stato possibile aggiornare i dati dell'utente\n" + e);
//            dbConnector.closeResources(null, null, connection);
//            return false;
//        }        
//        
//        return true;
//    }
//    
//    
//    public boolean aggiornaColonnaUtente(String idUtente, String nomeColonna, Object nuovoValore) throws Exception {
//
//        if (connection == null) {
//        	return false;
//        }
//        
//        try 
//        {
//        	String cmd = "UPDATE Utente SET " + nomeColonna + "=? WHERE idUtente=?";        	
//            PreparedStatement pstmt = connection.prepareStatement(cmd);
//            pstmt.setObject(1, nuovoValore);
//            pstmt.setString(2, idUtente);
//            pstmt.executeUpdate();            
//            System.out.println("L'aggiornamento è andato a buon fine.\n");
//            dbConnector.closeResources(pstmt, null, connection);
//        } 
//        catch (SQLException e) 
//        {
//        	System.out.println("Non è stato possibile aggiornare la colonna " + nomeColonna + "\n" + e);
//        	dbConnector.closeResources(null, null, connection);
//        	return false;
//        } 
//        
//        return true;
//    }
//    
//    public boolean eliminaUtente(Utente utente) throws Exception {
//    	
//    	if (connection == null) {
//    		return false;
//    	}
//
//        try 
//        {
//        	String cmd = "DELETE FROM Utente WHERE idUtente=?";        	
//            PreparedStatement pstmt = connection.prepareStatement(cmd);
//            pstmt.setString(1, utente.getIdUtente());
//            pstmt.executeUpdate();            
//            System.out.println("L'eliminazione è andata a buon fine.\n");            
//            dbConnector.closeResources(pstmt, null, connection);                
//        } 
//        catch (SQLException e) 
//        {
//        	System.out.println("Non è stato possibile eliminare l'utente\n" + e);
//        	dbConnector.closeResources(null, null, connection);
//        	return false;
//        }    
//        
//        return true;
//    }
    
    public Utente getById(String id) throws Exception {	
    	Utente user = null;
    	
        connection = PgDatabaseConnector.getConnection();
    	if (connection == null) {
    		return user;
    	}
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	try {
			String cmd = 
					"SELECT * " + 
					"FROM Utente " + 
					"WHERE idUtente = ?";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setInt(1, Integer.parseInt(id));
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = createUser(rs);
				printUser(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);;
		}
		
    	return user;
    }
    
    public Utente getByUsername(String username) throws Exception {	
    	Utente user = null;
    	
        connection = PgDatabaseConnector.getConnection();
    	if (connection == null) {
    		return user;
    	}

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

		try	{
			String cmd = 
					"SELECT * " + 
					"FROM Utente " + 
					"WHERE username = ?";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = createUser(rs);
				printUser(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);;
		}
    	
    	return user;
    }
    
    public Utente getByEmail(String email) throws Exception { 	
    	Utente user = null;
    	
        connection = PgDatabaseConnector.getConnection();
    	if (connection == null) {
    		return user;
    	}
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

		try	{
			String cmd = 
					"SELECT * " + 
					"FROM Utente " + 
					"WHERE email = ?";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = createUser(rs);
				printUser(user); 
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);;
		}
    	
    	return user;
    }
    
    public boolean areCredentialsCorrect(String username, String password) throws Exception {
        connection = PgDatabaseConnector.getConnection();
    	if (connection == null) {
    		return false;
    	}
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

		try {
			String cmd = 
					"SELECT * " + 
					"FROM Utente " + 
					"WHERE username = ? " +  
					"AND password=?";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);;
		}
		
		return false;
    }
    
    private Utente createUser(ResultSet rs) throws Exception {
    	return new Utente(
    			String.valueOf(rs.getInt("idUtente")),
    			rs.getString("email"),    
				rs.getString("username"), 
				rs.getString("password"), 
				rs.getString("nome"), 
			    rs.getString("cognome"),  
			    rs.getDate("dataNascita").toLocalDate(), 
			    rs.getDate("dataIscrizione").toLocalDate(), 
			    rs.getString("tipo")
    	);
    }
    
    private void printUser(Utente user) {
        System.out.println("email: " + user.getEmail() +
                		   "\nusername: " + user.getUsername() +
                           "\npassword: " + user.getPassword() +
 		                   "\nnome: " + user.getNome() +
		                   "\ncognome: " + user.getCognome() +
		                   "\ndataNascita: " + user.getDataNascita() +
		                   "\ndataIscrizione: " + user.getDataIscrizione() +
		                   "\ntipo: " + user.getTipo() +
		                   "\n"
		);
    }

}