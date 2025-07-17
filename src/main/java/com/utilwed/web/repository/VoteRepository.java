package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.utilwed.web.Entity.community.VoteType;

public class VoteRepository extends BaseRepository{
	
//	private String jdbcUrl = "jdbc:mysql://localhost:3306/ourlibrary?serverTimezone=Asia/Seoul";
//	private String dbUser = "root";
//	private String dbPassword = "1234";
//	
//	public VoteRepository() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		}catch (ClassNotFoundException e) {
//            System.err.println("MySQL JDBC Driver not found. Make sure mysql-connector-j-8.0.x.jar is in WEB-INF/lib.");
//            e.printStackTrace();
//            throw new RuntimeException("Failed to load JDBC driver", e);
//        }
//	}

	public int saveVote(int userId, int postId, VoteType voteType, Connection conn) {
		String sql = "INSERT INTO vote(user_id, post_id, vote_type) VALUES(?, ?, ?)";
		
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			pstmt.setInt(1, userId);
			pstmt.setInt(2, postId);
			pstmt.setString(3, voteType.name());
				
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
				e.printStackTrace();
			}
			
			return -1;
		
	}

	public Date getCreatedAtByUserId(int userId) {
		String sql = "SELECT created_at FROM vote WHERE user_id = ? ORDER BY created_at DESC LIMIT 1";
		
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, userId);
				
				
				
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					Date createdAt = rs.getTimestamp("created_at");
					return createdAt;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
}
