package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import util.PgDatabaseConnector;

public class RichiestaDiAccessoDAO {

	private Connection connection;
	private PgDatabaseConnector dbConnector;
	
	public RichiestaDiAccessoDAO() {
		dbConnector = new PgDatabaseConnector();
	}

	public boolean insert(String idGruppo, String idMittente, String idDestinatario) {
		connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return false;
		}
		
		PreparedStatement pstmt = null;
		
		try {
			String cmd = 
					"INSERT INTO RichiestaDiAccesso " +
					"(idMittente, idDestinatario, idGruppo, dataOraInvio, stato, dataAccettazione) " +
					"VALUES (?, ?, ?, ?, ?, ?)";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(idMittente));
            pstmt.setInt(2, Integer.parseInt(idDestinatario));
            pstmt.setInt(3, Integer.parseInt(idGruppo));
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
	
	public boolean hasPendingAccessRequest(String idGruppo, String idMittente) {
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
					"AND idMittente = ? " +
					"AND stato = 'in_attesa'";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(idGruppo));
            pstmt.setInt(2, Integer.parseInt(idMittente));
            
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
