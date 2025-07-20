package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utilwed.web.Entity.User;

public class UserRepository extends BaseRepository{
	
	public boolean saveUser(String username, String password, String email, String nickname) throws SQLException{
		String sql = "INSERT INTO user (username, password, email, nickname) VALUES(?, ?, ?, ?)";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, nickname);
			
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected > 0;
		}
		
	}
	
	public int findUserIdByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT id FROM user WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        
        return 0;
    }
	
	
	public User findUserByUserName(String username) throws SQLException {
		String sql = "SELECT * FROM user WHERE username = ?";
		try (Connection conn = getConnection();
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
			
		} 

		
	}
	
	public boolean updateUser(int userId, User user) throws SQLException {
		String sql = "UPDATE user SET username = ?, password = ?, email=?, nickname=? " +
				"where id=?";
		
		int result = 0;
		
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){		
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getNickname());
			pstmt.setInt(5, userId);
			
			result = pstmt.executeUpdate();
			
			return result > 0;
		}
		
	}
	
	public boolean deleteUser(int userId, String password) throws SQLException {
		String sql = "DELETE FROM user WHERE id = ? AND password = ?";
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setInt(1, userId);
			pstmt.setString(2, password);

			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected > 0;
			
		} 
		
	}
	
}
