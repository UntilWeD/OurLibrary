package com.utilwed.web.controller.community.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.repository.CommentRepository;
import com.utilwed.web.service.CommentService;

@WebServlet("/category/list/post/comment/delete")
public class CommentDeleteServlet extends HttpServlet{

	
	private CommentService commentService;
	
	@Override
	public void init() throws ServletException {
		CommentRepository commentRepository = new CommentRepository();
		this.commentService = new CommentService(commentRepository);	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = request.getParameter("c");
		int postId = Integer.parseInt(request.getParameter("po"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		
		
		int deleteComment = commentService.deleteComment(commentId); 
		
		if (deleteComment > 0) {
			response.sendRedirect("/category/list/post?c=" +categoryId+ "&po=" + postId);
		} else {
			// 오류시 오류페이지로 이동할 수 있게 후에 수정
			response.sendRedirect("/");
		}
		
		
	}
}
