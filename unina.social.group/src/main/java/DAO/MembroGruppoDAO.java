package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.PgDatabaseConnector;

public class MembroGruppoDAO {

	private Connection connection;
	private PgDatabaseConnector dbConnector;
	
	public MembroGruppoDAO() {
		dbConnector = new PgDatabaseConnector();
	}
	
	public boolean isMember(String idGruppo, String idUtente) {		
		connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return false;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT COUNT(*) "+ 
					"FROM MembroGruppo " +
					"WHERE idGruppo = ? " + 
					"AND idUtente = ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(idGruppo));
            pstmt.setInt(2, Integer.parseInt(idUtente));
            
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1)>0;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return false;
	}
	
	public List<String> getUserGroupIds(String idUtente) {
		List<String> groupsIds = new ArrayList<>();
		
		connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return groupsIds;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT idGruppo "+ 
					"FROM MembroGruppo " +
					"WHERE idUtente = ?";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(idUtente));
            
			rs = pstmt.executeQuery();

            while (rs.next()) {
            	groupsIds.add(rs.getString("idGruppo"));
            }
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return groupsIds;
	}
}
 