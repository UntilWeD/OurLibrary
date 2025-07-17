package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.utilwed.web.Entity.community.Comment;

public class CommentRepository extends BaseRepository{
	
	public int saveComment(Comment comment) {
		String sql = "INSERT INTO comment (content, user_id, post_id) VALUES (?, ?, ?)";
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
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public List<Comment> getCommentList(int postId, int commentPage){
		String sql = "SELECT C.id, C.content, C.created_at, C.user_id, C.post_id, U.username "
				+ "FROM comment C INNER JOIN user U "
				+ "ON c.user_id = u.id "
				+ "WHERE post_id = ? "
				+ "ORDER BY created_at DESC "
				+ "LIMIT ? OFFSET ?";
		List<Comment> result = new ArrayList<Comment>();
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
				PreparedStatement pstmt = con.prepareStatement(sql);){
			
				pstmt.setInt(1, postId);
				pstmt.setInt(2, 20);
				pstmt.setInt(3, 20 * (commentPage-1));
				
				try(ResultSet rs = pstmt.executeQuery();){
					while(rs.next()) {
						int id = rs.getInt("id");
						String content = rs.getString("content");
						Date createdAt = rs.getTimestamp("created_at");
						int userId = rs.getInt("user_id");
						int resultPostId = rs.getInt("post_id");
						String userName = rs.getString("username");
						
						Comment comment = new Comment(
								id,
								content,
								createdAt,
								userId,
								resultPostId,
								userName
						);
						
						result.add(comment);
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return result;
	}

	public boolean updateComment(int commentId, String content) {
		String sql = "UPDATE comment SET content = ? WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, content);
			pstmt.setInt(2, commentId);
			
			int rowsAfftected = pstmt.executeUpdate();
			
			return rowsAfftected > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public int deleteComment(int commentId) {
		String sql = "DELETE FROM comment WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, commentId);
			
			int rowsAfftected = pstmt.executeUpdate();
			
			return rowsAfftected;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

}
