package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utilwed.web.Entity.community.Comment;

public class CommentRepository {
	
	private String jdbcUrl = "jdbc:mysql://localhost:3306/ourlibrary?useSSL=false&serverTimezone=UTC";
	private String dbUser = "root";
	private String dbPassword = "1234";
	
	public CommentRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure mysql-connector-j-8.0.x.jar is in WEB-INF/lib.");
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
	}
	
	public int saveComment(Comment comment) {
		String sql = "INSERT INTO comment (content, userId, postId) VALUES (?, ?, ?)";
		try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getUserId());
			pstmt.setInt(3, comment.getPostId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
				try(ResultSet rs = pstmt.getGeneratedKeys()){
					if(rs.next()) {
						int generatedId = rs.getInt(1);
						return generatedId;
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return -1;
	}

}
