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

import com.utilwed.web.Entity.community.Post;

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
	
	public List<Post> getPostList(int categoryId, String field, String query, int page){
		
		String sql = "SELECT * FROM post "
				+ "WHERE category_id = ? AND " + field + " LIKE ? " +
				"ORDER BY created_at DESC " +
				"LIMIT ? OFFSET ?";
		List<Post> result = new ArrayList<>();
		
	    // SQL 필드명 화이트리스트 체크
	    if (!List.of("title", "content", "nickname").contains(field)) {
	        throw new IllegalArgumentException("허용되지 않은 검색 필드");
	    }
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			PreparedStatement st = con.prepareStatement(sql);){
		
			st.setInt(1, categoryId);
			st.setString(2, "%" + query + "%");
			st.setInt(3, 20);
			st.setInt(4, (page-1) * 20);
			
			try(ResultSet rs = st.executeQuery();){
				while(rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String content = rs.getString("content");
					String nickname = rs.getString("nickname");
					Date createdAt = rs.getTimestamp("created_at");
					Date updatedAt = rs.getTimestamp("updated_at");
					int likeCount = rs.getInt("like_count");
					int dislikeCount = rs.getInt("dislike_count");
					int view = rs.getInt("view");
					int userId = rs.getInt("user_id");
					int postCategoryId = rs.getInt("category_id");
					int commentCount = rs.getInt("comment_count");
					
					Post post = new Post(
								id,
								title,
								content,
								nickname,
								createdAt,
								updatedAt,
								likeCount,
								dislikeCount,
								commentCount,
								view,
								userId,
								postCategoryId
					);
					
					result.add(post);
					
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int getPostCount(int categoryId, String field, String query) {
	    int count = 0;

	    if (!List.of("title", "content", "nickname").contains(field)) {
	        throw new IllegalArgumentException("허용되지 않은 검색 필드");
	    }

	    String sql = "SELECT COUNT(*) FROM post WHERE category_id = ? AND " + field + " LIKE ?";

	    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
	         PreparedStatement st = con.prepareStatement(sql)) {

	        st.setInt(1, categoryId);
	        st.setString(2, "%" + query + "%");

	        try (ResultSet rs = st.executeQuery()) {
	            if (rs.next()) {
	                count = rs.getInt(1);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	 
	public Post getPost(int categoryId, int postId) {
		String sql = "SELECT * FROM post WHERE category_id = ? AND id = ?";
		Post post = null;
		try (Connection con = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			PreparedStatement st = con.prepareStatement(sql);){
			
				st.setInt(1, categoryId);
				st.setInt(2, postId);
				
				try(ResultSet rs = st.executeQuery();){
					if (rs.next()) {
						int id = rs.getInt("id");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String nickname = rs.getString("nickname");
						Date createdAt = rs.getDate("created_at");
						Date updatedAt = rs.getDate("updated_at");
						int likeCount = rs.getInt("like_count");
						int dislikeCount = rs.getInt("dislike_count");
						int commentCount = rs.getInt("comment_count");
						int view = rs.getInt("view");
						int userId = rs.getInt("user_id");
						int postCategoryId = rs.getInt("category_id");
						
						post = new Post(
							id,
							title,
							content,
							nickname,
							createdAt,
							updatedAt,
							likeCount,
							dislikeCount,
							commentCount,
							view,
							userId,
							postCategoryId
						);
						
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return post;
		
		
	}

	public boolean updatePost(Post post) {
		String sql = "UPDATE post SET title = ?, content = ? WHERE user_id = ? AND id = ?";
		
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
				pstmt.setString(1, post.getTitle());
				pstmt.setString(2, post.getContent());
				pstmt.setInt(3, post.getUserId());
				pstmt.setInt(4, post.getId());
				
				
				int rowsAffected = pstmt.executeUpdate();
	
				return rowsAffected > 0;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return false;
	}

	public int deletePost(int postId) {
		String sql = "DELETE FROM post WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
				pstmt.setInt(1, postId);

				
				
				int rowsAffected = pstmt.executeUpdate();
	
				return rowsAffected;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return -1;

	}

	public void updatePostViewCount(int postId) {
		String sql = "UPDATE post SET view = view + 1 WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
				PreparedStatement pstmt = conn.prepareStatement(sql);){
				pstmt.setInt(1, postId);
				
				
				int rowsAffected = pstmt.executeUpdate();
	
				if(rowsAffected > 0) {
					return;
				} else{
					throw new SQLException("조회수를 올리던 중 오류 발생");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	public void updatePostCommentCount(int postId) {
		String sql = "UPDATE post SET comment_count = comment_count + 1 WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
				PreparedStatement pstmt = conn.prepareStatement(sql);){
				pstmt.setInt(1, postId);
				
				
				int rowsAffected = pstmt.executeUpdate();
	
				if(rowsAffected > 0) {
					return;
				} else{
					throw new SQLException("댓글수를 올리던 중 오류 발생");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void decrementCommentCount(int postId) {
		String sql = "UPDATE post SET comment_count = comment_count - 1 WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
				PreparedStatement pstmt = conn.prepareStatement(sql);){
				pstmt.setInt(1, postId);
				
				
				int rowsAffected = pstmt.executeUpdate();
	
				if(rowsAffected > 0) {
					return;
				} else{
					throw new SQLException("댓글수를 감소하던 중 오류 발생");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
}
