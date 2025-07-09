package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utilwed.web.Entity.User;

public class UserRepository {
	
	private String jdbcUrl = "jdbc:mysql://localhost:3306/ourlibrary?useSSL=false&serverTimezone=UTC";
	private String dbUser = "root";
	private String dbPassword = "1234";
	
	public UserRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure mysql-connector-j-8.0.x.jar is in WEB-INF/lib.");
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
	}
	
	public boolean saveUser(String username, String password, String email, String nickname) {
		String sql = "INSERT INTO user (username, password, email, nickname) VALUES(?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, nickname);
			
			
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT id FROM user WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
	
	public User findUserByUserName(String username) {
		String sql = "SELECT * FROM user WHERE username = ?";
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql);){		
			
			pstmt.setString(1, username);
			
			
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					String resultUsername = rs.getString("username");
					String resultPassword = rs.getString("password");
					String resultEmail = rs.getString("email");
					String resultNickname = rs.getString("nickname");
					User user = new User(resultUsername, resultPassword, resultEmail, resultNickname);
					return user;
				} else {
					return null;
				}
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
		
	}
	
	public boolean updateUser(int userId, User user) {
		String sql = "UPDATE user SET username = ?, password = ?, email=?, nickname=? " +
				"where id=?";
		
		int result = 0;
		
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql);){		
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getNickname());
			pstmt.setInt(5, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result > 0;
		
	}
	
	public boolean deleteUser(int userId, String password) {
		String sql = "DELETE FROM user WHERE id = ? AND password = ?";
		try {
			
			Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, password);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
