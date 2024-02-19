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
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(5000); 
        config.setIdleTimeout(300000); 
        config.setMaxLifetime(1800000);
        config.setLeakDetectionThreshold(60 * 1000); 

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
    	try {
            return dataSource.getConnection();
    	} catch(SQLException e) {
    		e.printStackTrace();;
        	return null;
    	}
    }
    
    public void closeResources(ResultSet rs, Statement stmt, Connection connection) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

}
