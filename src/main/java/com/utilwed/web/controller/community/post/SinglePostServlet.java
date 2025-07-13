package com.utilwed.web.controller.community.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post")
public class SinglePostServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		this.postService = new PostService(postRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int categoryId = Integer.parseInt(request.getParameter("c"));
		int postId = Integer.parseInt(request.getParameter("po"));
		
		Post post = postService.getPost(categoryId, postId);
		request.setAttribute("po", post);
		
		request.getRequestDispatcher("/WEB-INF/view/community/post/post.jsp")
		.forward(request, response);
		
		
	}
	
}
