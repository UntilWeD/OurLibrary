package com.utilwed.web.controller.community.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post/save")
public class PostSaveServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		this.postService = new PostService(postRepository);
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/community/post/postForm.jsp")
		.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int categoryId = Integer.parseInt(request.getParameter("c"));
		String nickname = (String) session.getAttribute("loggedInUser");
		int userId = (int) session.getAttribute("userId");
		int savedPostId = 0;
		
		Post post = new Post(
				title,
				content,
				nickname,
				userId,
				categoryId
				);

		savedPostId = postService.savePost(post);
		
			
		
		if(savedPostId > 0) {
			response.sendRedirect("/category/list/post?c=" + categoryId + "&po=" + savedPostId);
		} else {
			response.sendRedirect("/category/list/post");
		}
		
	}
	
}
