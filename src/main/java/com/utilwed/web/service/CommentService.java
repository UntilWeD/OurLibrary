package com.utilwed.web.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.utilwed.web.Entity.community.Comment;
import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.CommentRepository;
import com.utilwed.web.repository.PostRepository;

public class CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private BaseRepository baseRepository;

	public CommentService(CommentRepository commentRepository, PostRepository postRepository,
			BaseRepository baseRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.baseRepository = baseRepository;
	}

	public int saveComment(Comment comment, int postId) throws SQLException{
		Connection conn = null;
		int savedCommentId = - 1;
		try {
			conn = baseRepository.getConnection();
			conn.setAutoCommit(false);
			
			savedCommentId = commentRepository.saveComment(comment);
			postRepository.updatePostCommentCount(postId);
			
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.setAutoCommit(true);
			conn.close();
		}
		
		return savedCommentId;
	}
	
	public List<Comment> getCommentList(int postId, int commentPage){
		return commentRepository.getCommentList(postId, commentPage);
	}
	
	public boolean updateComment(int commentId, String content) {
		return commentRepository.updateComment(commentId, content);
	}
	
	public int deleteComment(int commentId, int postId) {
		int affectedRows = commentRepository.deleteComment(commentId);
		postRepository.decrementCommentCount(postId);
		return affectedRows;
	}
	
	
}
