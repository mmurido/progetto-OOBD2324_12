package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Post;

public class PostDAO {
	
	private Connection connection;
	private PgDatabaseConnector dbConnector;
	
	public PostDAO() {
		dbConnector = new PgDatabaseConnector();
	}
	
	public boolean insert(Post post) {
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
        	return false;
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
    	try {
            String cmd = 
            		"INSERT INTO Post (idGruppo, usernameAutore, dataOraPubblicazione, testo)"
         		       + "VALUES (?, ?, now(), ?) RETURNING idPost";
            
    		pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(post.getGroup().getId()));
            pstmt.setString(2, post.getAuthor().getUsername());
            pstmt.setString(3, post.getText());
            
            rs = pstmt.executeQuery(); 
            
            if (rs.next()) {
            	post.setId(String.valueOf(rs.getInt("idPost")));
                return true;
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
			dbConnector.closeResources(rs, pstmt, connection);;
    	}
    	
    	return false;
    }
	
	public Object[] getMostCommentedPostOfGroupInMonthYear(String groupId, int month, int year) {
		Object[] postAndCommentCount = null;
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return postAndCommentCount;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
			String cmd = 
					"SELECT P.*, COUNT(*) as commentCount " +
					"FROM Commento C JOIN Post P " +
					"ON C.idpost = P.idpost " +
					"WHERE P.idGruppo = ? " +
					"AND C.dataOra BETWEEN P.dataOraPubblicazione AND ? " +
					"GROUP BY P.idPost " +
					"ORDER BY COUNT(*) DESC " +
					"LIMIT 1";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setInt(1, Integer.parseInt(groupId));
			pstmt.setDate(2, Date.valueOf(YearMonth.of(year, month).atEndOfMonth()));
            
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            Post post = createPost(rs);
	            int commentCount = rs.getInt("commentCount");
	            postAndCommentCount = new Object[] {post, commentCount};
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return postAndCommentCount;
	}
	
	public Object[] getLeastCommentedPostOfGroupInMonthYear(String groupId, int month, int year) {
		Object[] postAndCommentCount = null;
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return postAndCommentCount;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
			String cmd = 
					"SELECT P.*, COUNT(*) as commentCount " +
					"FROM Commento C JOIN Post P " +
					"ON C.idpost = P.idpost " +
					"WHERE P.idGruppo = ? " +
					"AND C.dataOra BETWEEN P.dataOraPubblicazione AND ? " +
					"GROUP BY P.idPost " +
					"ORDER BY COUNT(*) ASC " +
					"LIMIT 1";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setInt(1, Integer.parseInt(groupId));
			pstmt.setDate(2, Date.valueOf(YearMonth.of(year, month).atEndOfMonth()));
            
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            Post post = createPost(rs);
	            int commentCount = rs.getInt("commentCount");
	            postAndCommentCount = new Object[] {post, commentCount};
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return postAndCommentCount;
	}
	
	public Object[] getMostLikedPostOfGroupInMonthYear(String groupId, int month, int year) {
		Object[] postAndLikeCount = null;
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return postAndLikeCount;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
			String cmd = 
				    "SELECT P.*, COUNT(*) AS likeCount " +
				    "FROM \"Like\" L JOIN Post P " +
				    "ON L.idpost = P.idpost " +
		    	    "WHERE P.idGruppo = ? " +
		    	    "AND L.data BETWEEN P.dataOraPubblicazione AND ? " +
		    	    "GROUP BY P.idPost " +
		    	    "ORDER BY likeCount DESC " +
		    	    "LIMIT 1";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setInt(1, Integer.parseInt(groupId));
			pstmt.setDate(2, Date.valueOf(YearMonth.of(year, month).atEndOfMonth()));
            
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            Post post = createPost(rs);
	            int likeCount = rs.getInt("likeCount");
	            postAndLikeCount = new Object[] {post, likeCount};
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return postAndLikeCount;
	}
	
	public Object[] getLeastLikedPostOfGroupInMonthYear(String groupId, int month, int year) {
		Object[] postAndLikeCount = null;
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return postAndLikeCount;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
			String cmd = 
				    "SELECT P.*, COUNT(*) AS likeCount " +
				    "FROM \"Like\" L JOIN Post P " +
				    "ON L.idpost = P.idpost " +
		    	    "WHERE P.idGruppo = ? " +
		    	    "AND L.data BETWEEN P.dataOraPubblicazione AND ? " +
		    	    "GROUP BY P.idPost " +
		    	    "ORDER BY likeCount ASC " +
		    	    "LIMIT 1";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setInt(1, Integer.parseInt(groupId));
			pstmt.setDate(2, Date.valueOf(YearMonth.of(year, month).atEndOfMonth()));
            
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            Post post = createPost(rs);
	            int likeCount = rs.getInt("likeCount");
	            postAndLikeCount = new Object[] {post, likeCount};
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return postAndLikeCount;
	}
	
	public int getPostCountOfGroupInMonthYear(String groupId, int month, int year) {
		int postCount = 0;
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return postCount;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
			String cmd = 
					"SELECT COUNT(*) AS postCount " +
					"FROM Post " +
					"WHERE idGruppo = ? " +
					"AND EXTRACT(year from dataOraPubblicazione) = ? " +
					"AND EXTRACT(month from dataOraPubblicazione) = ?";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setInt(1, Integer.parseInt(groupId));
			pstmt.setInt(2, year);
			pstmt.setInt(3, month);
            
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            postCount = rs.getInt("postCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return postCount;
	}
	
	public Map<Integer, Integer> getPostsCountPerDay(String groupId, int month, int year) {
		Map<Integer, Integer> postsPerDay = new HashMap<>();	
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return postsPerDay;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
			String cmd = 
					"SELECT EXTRACT(DAY FROM dataOraPubblicazione) AS day, COUNT(*) AS postCount " +
					"FROM Post " +
					"WHERE idGruppo = ? " +
					"AND EXTRACT(YEAR FROM dataOraPubblicazione) = ? " +
					"AND EXTRACT(MONTH FROM dataOraPubblicazione) = ? " +
					"GROUP BY day;";
			
			pstmt = connection.prepareStatement(cmd);
			pstmt.setInt(1, Integer.parseInt(groupId));
			pstmt.setInt(2, year);
			pstmt.setInt(3, month);

			rs = pstmt.executeQuery();
			
            while (rs.next()) {
                int day = rs.getInt("day");
                int postCount = rs.getInt("postCount");
                postsPerDay.put(day, postCount);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return postsPerDay;
	}
	
	public List<Post> getAllPostsOfGroup(String idGruppo) {
		List<Post> posts = new ArrayList<>();
		
        connection = PgDatabaseConnector.getConnection();
		if (connection == null) {
			return posts;
		}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		try {
			String cmd = 
					"SELECT * " +
					"FROM Post " +
					"WHERE idGruppo = ? " +
					"ORDER BY dataOraPubblicazione DESC";
			
			pstmt = connection.prepareStatement(cmd);
            pstmt.setInt(1, Integer.parseInt(idGruppo));
            
			rs = pstmt.executeQuery();

			while (rs.next()) {
				posts.add(createPost(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeResources(rs, pstmt, connection);
		}
		
		return posts;
	}
	
	private Post createPost(ResultSet rs) {
		GroupDAO gruppoDAO = new GroupDAO();
		UserDAO utenteDAO = new UserDAO();
		Post post = null;
		
		try {
			post = new Post(
					String.valueOf(rs.getInt("idPost")),
					rs.getString("testo"),
					rs.getTimestamp("dataOraPubblicazione").toLocalDateTime(),
					gruppoDAO.getById(String.valueOf(rs.getInt("idGruppo"))),
					utenteDAO.getByUsername(rs.getString("usernameAutore"))
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return post;
	}
	
	
}
