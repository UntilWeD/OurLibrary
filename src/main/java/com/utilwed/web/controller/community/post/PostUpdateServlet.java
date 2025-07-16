package com.utilwed.web.controller.community.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post/edit")
public class PostUpdateServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		AttachmentRepository attachmentRepository = new AttachmentRepository();
		this.postService = new PostService(postRepository, attachmentRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("c"));
		int postId = Integer.parseInt(request.getParameter("po"));
		try {
			Post post = postService.getPost(categoryId, postId);
			request.setAttribute("po", post);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
		request.getRequestDispatcher("/WEB-INF/view/community/post/postForm.jsp")
		.forward(request, response);
		
	}	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String nickname = (String) session.getAttribute("loggedInUser");
		int id = Integer.parseInt(request.getParameter("po"));
		int categoryId = Integer.parseInt(request.getParameter("c"));
		int userId = (int) session.getAttribute("userId");
		
		Post post = new Post(
				id,
				title,
				content,
				nickname,
				userId,
				categoryId
				);

		boolean updated = postService.updatePost(post);
		
		if(updated) {
			response.sendRedirect("/category/list/post?c=" + categoryId + "&po=" + id);
		} else {
			response.sendRedirect("/category/list/post");
		}
	}
	
}
