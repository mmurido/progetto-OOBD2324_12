package util;

import java.sql.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PgDatabaseConnector {
	
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/UninaSocialGroupDB");
        config.setUsername("postgres");
        config.setPassword("!Teresa!002!");
        config.setMaximumPoolSize(5);
        dataSource = new HikariDataSource(config);
    }

<<<<<<< Updated upstream
    public static Connection getConnection() throws SQLException {
    	try 
    	{
=======
    public static Connection getConnection() {
    	try {
>>>>>>> Stashed changes
            return dataSource.getConnection();
    	} 
    	catch(SQLException e) 
    	{
    		System.out.println("Connessione alla base di dati fallita." + e);
        	return null;
    	}
    }
    
    public void closeResources(Statement stmt, ResultSet rs, Connection con) {
    	try 
    	{
    		if (stmt != null && !stmt.isClosed()) {
    			stmt.close();
    		}
    		
    		if (rs != null && !rs.isClosed()) {
    			rs.close();
    		}
    		
    		if (con != null && !con.isClosed()) {
    			con.close();
    		}
    	} 
    	catch (SQLException e) 
    	{
    		System.out.println(e);
    	}
    }

}
