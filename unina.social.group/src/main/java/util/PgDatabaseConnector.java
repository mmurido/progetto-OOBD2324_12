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


    public static Connection getConnection() {
    	try {
            return dataSource.getConnection();
    	} 
    	catch(SQLException e) 
    	{
    		System.out.println("Connessione alla base di dati fallita." + e);
        	return null;
    	}
    }
    
    public void closeResources(ResultSet rs, Statement stmt, Connection con) {
    	try 
    	{
    		
    		if (rs != null && !rs.isClosed()) {
    			rs.close();
    		}
    		
    		if (stmt != null && !stmt.isClosed()) {
    			stmt.close();
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
