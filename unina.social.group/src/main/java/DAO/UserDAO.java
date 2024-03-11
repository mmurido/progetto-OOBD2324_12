package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO{
	
	private Connection connection;
	private PgDatabaseConnector dbConnector;
	
	public UserDAO() {
		dbConnector = new PgDatabaseConnector();
	}
	
	public boolean insert(User user) {
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
        	return false;
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
    	try {
            String cmd = 
            		"INSERT INTO Utente (email, username, password, nome," +
            		" cognome, dataNascita) VALUES (?, ?, ?, ?, ?, ?)";
            
    		pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getName());
            pstmt.setString(5, user.getSurname());
            pstmt.setDate(6, Date.valueOf(user.getBirthDate()));  

            
            rs = pstmt.executeQuery(); 
            
            if (rs.next()) {
                return true;
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
			dbConnector.closeResources(rs, pstmt, connection);;
    	}
    	
    	return false;
    }
	    
	public User getByUsername(String username) {
		User user = null;

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
					"WHERE username = ?";

			pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, username);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = createUser(rs);
//				printUser(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}

		return user;
	}

	public User getByEmail(String email) {
		User user = null;

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
					"WHERE email = ?";

			pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = createUser(rs);
				printUser(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}

		return user;
	}

	public boolean authenticate(String username, String password) {
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
			        "AND password = ?";

			pstmt = connection.prepareStatement(cmd);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}

		return false;
	}

	private User createUser(ResultSet rs) {
		User user = null;

		try {
			user = new User(
					rs.getString("email"), 
					rs.getString("username"),
					rs.getString("password"), 
					rs.getString("nome"), 
					rs.getString("cognome"),
					rs.getDate("dataNascita").toLocalDate() );
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	private void printUser(User user) {
		System.out.println(
				"email: " + user.getEmail() + 
				"\nusername: " + user.getUsername() + 
				"\npassword: " + user.getPassword() + 
				"\nnome: " + user.getName() + 
				"\ncognome: " + user.getSurname() +
				"\ndataNascita: " + user.getBirthDate() + 
				"\n");
	}
}