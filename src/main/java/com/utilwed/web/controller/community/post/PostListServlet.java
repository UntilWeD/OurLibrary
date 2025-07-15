package com.utilwed.web.controller.community.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.CommentRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.CommentService;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list")
public class PostListServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		this.postService = new PostService(postRepository);

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId_ = request.getParameter("c");
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_ != null && !field_.equals(""))
			field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		int categoryId = 1;
		if(categoryId_ != null && !categoryId_.equals(""))
			categoryId = Integer.parseInt(categoryId_);
		
		
		// 1. PostList 가져오기
		// - 전체 post 20개씩 가져와서 jsp로 전송
		List<Post> list = postService.getPostList(categoryId, field, query, page);
		request.setAttribute("list", list);
		
		// - 전체 post 개수 가져와서 jsp로 전송
		int totalPostSize = postService.getPostCount(field, query, categoryId);
		request.setAttribute("count", totalPostSize);
		
		
		request.getRequestDispatcher("/WEB-INF/view/community/post/postList.jsp")
		.forward(request, response);
		
	}
	
}
