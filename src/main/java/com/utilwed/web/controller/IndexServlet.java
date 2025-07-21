package com.utilwed.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.CategoryRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/")
public class IndexServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		BaseRepository baseRepository = new BaseRepository();
		CategoryRepository categoryRepository = new CategoryRepository();
		AttachmentRepository attachmentRepository = new AttachmentRepository();
		this.postService = new PostService(postRepository, attachmentRepository, baseRepository, categoryRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId = 0;
		List<Post> bestPostList = null;
		
		try {
			HttpSession session = request.getSession(false);
			if(session != null && session.getAttribute("userId") instanceof Integer) {
				userId = (Integer) session.getAttribute("userId");
			}
			
			if(userId != null) {
				bestPostList = postService.getBestPostList();
				request.setAttribute("bestPostList", bestPostList);
				
			} else {
				
			}
			
			request.getRequestDispatcher("/WEB-INF/view/main/index.jsp")
			.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	
	
}
