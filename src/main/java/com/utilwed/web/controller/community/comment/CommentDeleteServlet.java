package com.utilwed.web.controller.community.comment;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.CommentRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.CommentService;

@WebServlet("/category/list/post/comment/delete")
public class CommentDeleteServlet extends HttpServlet{

	private CommentService commentService;
	
	@Override
	public void init() throws ServletException {
		CommentRepository commentRepository = new CommentRepository();	
		PostRepository postRepository = new PostRepository();
		BaseRepository baseRepository = new BaseRepository();
		this.commentService = new CommentService(commentRepository, postRepository, baseRepository);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("c");
			int postId = Integer.parseInt(request.getParameter("po"));
			int commentId = Integer.parseInt(request.getParameter("commentId"));
			
			
			int deleteComment = -1;
			
			// 트랜잭션 적용
			deleteComment = commentService.deleteComment(commentId, postId);

			
			if (deleteComment > 0) {
				response.sendRedirect("/category/list/post?c=" +categoryId+ "&po=" + postId);
			} else {
				// 오류시 오류페이지로 이동할 수 있게 후에 수정
				response.sendRedirect("/");
			}
		} catch (SQLException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": DB와 관련하여 오류가 발생하였습니다. -> " + e.getMessage());
		} catch (NullPointerException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있습니다. -> " + e.getMessage());
		} catch (NumberFormatException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있거나 숫자변환 중 오류가 발생하였습니다. -> "+ e.getMessage());
		} catch (Exception e) { 
			throw new ServletException(this.getClass().getSimpleName() + ": 알 수 없는 오류가 발생하였습니다. -> "+ e.getMessage());
		}
		
		
	}
}
