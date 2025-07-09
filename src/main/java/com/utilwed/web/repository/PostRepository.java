package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utilwed.web.Entity.post.Post;

public class PostRepository {
	
	private String jdbcUrl = "jdbc:mysql://localhost:3306/ourlibrary?useSSL=false&serverTimezone=UTC";
	private String dbUser = "root";
	private String dbPassword = "1234";
	
	public PostRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure mysql-connector-j-8.0.x.jar is in WEB-INF/lib.");
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
	}
	
	public int savePost(Post post) {
		String sql = "INSERT INTO post (title, content, nickname, user_id, category_id) " +
					"VALUES(?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContent());
			pstmt.setString(3, post.getNickname());
			pstmt.setInt(4, post.getUserId());
			pstmt.setInt(5, post.getCategoryId());
			
			
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
			e.printStackTrace();
		}

		return -1;
	}
	
}
