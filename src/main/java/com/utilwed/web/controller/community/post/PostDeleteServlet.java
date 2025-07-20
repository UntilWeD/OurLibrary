package com.utilwed.web.controller.community.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post/delete")
public class PostDeleteServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		BaseRepository baseRepository = new BaseRepository();
		AttachmentRepository attachmentRepository = new AttachmentRepository();
		this.postService = new PostService(postRepository, attachmentRepository, baseRepository);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int postId = Integer.parseInt(request.getParameter("po"));
			int categoryId = Integer.parseInt(request.getParameter("c"));
			
			if(postService.deletePost(postId)> 0) {
				response.sendRedirect("/category/list?c=" + categoryId + "&p=1");
			} else {
				request.setAttribute("errorMessage", "DB에서 삭제하는데 오류가 생겼습니다. 해당 포스트에 오류가 있거나 ID가 존재하지 않는 포스트입니다.");
				response.sendRedirect("/category/list/post?c=" + categoryId + "&p=" + 1 + "&po=" + postId);
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
