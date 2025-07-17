package com.utilwed.web.controller.community.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post/delete")
public class PostDeleteServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		AttachmentRepository attachmentRepository = new AttachmentRepository();
		this.postService = new PostService(postRepository, attachmentRepository);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postId = Integer.parseInt(request.getParameter("po"));
		int categoryId = Integer.parseInt(request.getParameter("c"));
		
		try {
			int deletedRow = postService.deletePost(postId);
			
			if(deletedRow > 0) {
				response.sendRedirect("/category/list?c=" + categoryId + "&p=1");
			}
		} catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 게시물 ID입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "게시물 삭제 중 오류가 발생했습니다: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
	}
}
