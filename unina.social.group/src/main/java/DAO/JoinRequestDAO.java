package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JoinRequestDAO {

	private Connection connection;
	private PgDatabaseConnector dbConnector;
	
	public JoinRequestDAO() {
		dbConnector = new PgDatabaseConnector();
	}

	public boolean insert(String groupId, String senderUsername, String recipientUsername) {
		connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return false;
		}
		
		PreparedStatement pstmt = null;
		
		try {
			String cmd = 
					"INSERT INTO RichiestaDiAccesso " +
					"(usernameMittente, usernameDestinatario, idGruppo, dataOraInvio, stato, dataAccettazione) " +
					"VALUES (?, ?, ?, ?, ?, ?)";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setString(1, senderUsername);
            pstmt.setString(2, recipientUsername);
            pstmt.setInt(3, Integer.parseInt(groupId));
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(5, "in_attesa");
            pstmt.setDate(6, null);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e){
            e.printStackTrace(); 
		} finally {
			dbConnector.closeResources(null, pstmt, connection);
		}
		
		return false;
	}

	public boolean hasPendingAccessRequest(String groupId, String senderUsername) {
		connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return false;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT * "+ 
					"FROM RichiestaDiAccesso " +
					"WHERE idGruppo = ? " +
					"AND usernameMittente = ? " +
					"AND stato = 'in_attesa'";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(groupId));
            pstmt.setString(2, senderUsername);
            
			rs = pstmt.executeQuery();

			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return false;
	}
}
