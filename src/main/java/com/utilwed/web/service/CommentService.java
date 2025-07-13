package com.utilwed.web.service;

import java.util.List;

import com.utilwed.web.Entity.community.Comment;
import com.utilwed.web.repository.CommentRepository;

public class CommentService {

	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public int saveComment(Comment comment) {
		return commentRepository.saveComment(comment);
	}
	
	public List<Comment> getCommentList(Long postId){
		
		return null;
	}
	
	public boolean updateComment(int commentId) {
		return false;
	
	}
	
	
	
}
