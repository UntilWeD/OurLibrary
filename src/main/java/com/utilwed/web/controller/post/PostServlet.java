package com.utilwed.web.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.Entity.post.Post;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post/save")
public class PostServlet extends HttpServlet{
	
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
		System.out.println("제대로 실행됬나요");
		
		HttpSession session = request.getSession(false);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String nickname = request.getParameter("nickname");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int userId = (int) session.getAttribute("userId");

		Post post = new Post(
				title,
				content,
				nickname,
				userId,
				categoryId
				);
		
		int newPostId = postService.savePost(post);
		
		if(newPostId > 0) {
			response.sendRedirect("/category/list/post?cid= + " + categoryId + "&pid=" + newPostId);
		} else {
			response.sendRedirect("/category/list/post");
		}
		
	}
	
}
