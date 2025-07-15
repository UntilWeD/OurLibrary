package com.utilwed.web.service;

import java.util.List;

import com.utilwed.web.Entity.community.Comment;
import com.utilwed.web.repository.CommentRepository;
import com.utilwed.web.repository.PostRepository;

public class CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;

	
	
	public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	public int saveComment(Comment comment, int postId) {
		int savedCommentId = commentRepository.saveComment(comment);
		postRepository.updatePostCommentCount(postId);
		
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
