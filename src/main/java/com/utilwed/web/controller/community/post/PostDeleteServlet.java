package com.utilwed.web.controller.community.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post/delete")
public class PostDeleteServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		this.postService = new PostService(postRepository);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postId = Integer.parseInt(request.getParameter("po"));
		int categoryId = Integer.parseInt(request.getParameter("c"));
		
		int deletedRow = postService.deletePost(postId);
		
		if(deletedRow > 0) {
			response.sendRedirect("/category/list?c=" + categoryId + "&p=1");
		}
	}
}
