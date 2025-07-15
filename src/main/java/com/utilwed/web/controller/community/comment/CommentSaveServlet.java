package com.utilwed.web.controller.community.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.Entity.community.Comment;
import com.utilwed.web.repository.CommentRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.CommentService;

@WebServlet("/category/list/post/comment/save")
public class CommentSaveServlet extends HttpServlet{
	
	private CommentService commentService;
	
	@Override
	public void init() throws ServletException {
		CommentRepository commentRepository = new CommentRepository();	
		PostRepository postRepository = new PostRepository();
		this.commentService = new CommentService(commentRepository, postRepository);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String categoryId = request.getParameter("categoryId");
	
		String content = request.getParameter("content");
		int postId = Integer.parseInt(request.getParameter("postId"));
		int userId = (int) session.getAttribute("userId");
		
		
		Comment comment = new Comment(
				content,userId, postId
				);
		
		int commentId = commentService.saveComment(comment, postId);

		if (commentId > 0) {
			response.sendRedirect("/category/list/post?c=" +categoryId+ "&po=" + postId);
		} else {
			// 오류시 오류페이지로 이동할 수 있게 후에 수정
			response.sendRedirect("/");
		}
		
	}
}
