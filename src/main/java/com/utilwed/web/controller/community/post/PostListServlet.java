package com.utilwed.web.controller.community.post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list")
public class PostListServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		BaseRepository baseRepository = new BaseRepository();
		AttachmentRepository attachmentRepository = new AttachmentRepository();
		this.postService = new PostService(postRepository, attachmentRepository, baseRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
			
		} catch (NullPointerException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있습니다. -> " + e.getMessage());
		} catch (NumberFormatException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있거나 숫자변환 중 오류가 발생하였습니다. -> "+ e.getMessage());
		} catch (Exception e) { 
			throw new ServletException(this.getClass().getSimpleName() + ": 알 수 없는 오류가 발생하였습니다. -> "+ e.getMessage());
		}
		
	}
	
}
