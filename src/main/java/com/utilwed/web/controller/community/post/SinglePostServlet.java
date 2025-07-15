package com.utilwed.web.controller.community.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.Entity.community.Comment;
import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.CommentRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.CommentService;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post")
public class SinglePostServlet extends HttpServlet{
	
	private PostService postService;
	private CommentService commentService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		this.postService = new PostService(postRepository);
		// 이건 유의하도록 하자, 같은 객체로 의존성 
		CommentRepository commentRepository = new CommentRepository();
		this.commentService = new CommentService(commentRepository, postRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int categoryId = Integer.parseInt(request.getParameter("c"));
		int postId = Integer.parseInt(request.getParameter("po"));
		String commentPage_ = request.getParameter("cp");
		
		postService.updateView(postId);
		
		// 댓글 페이지
		int commentPage = 1;
		if(commentPage_ != null && !commentPage_.equals(""))
			commentPage = Integer.parseInt(commentPage_);
		
		
		//1. 포스트 가져오기
		Post post = postService.getPost(categoryId, postId);
		request.setAttribute("po", post);
		
		
		//2. 댓글 가져오기
		List<Comment> commentList = commentService.getCommentList(postId, commentPage);
		request.setAttribute("commentList", commentList);
		
		//3. 좋아요 가져오기
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/community/post/post.jsp")
		.forward(request, response);
		
		
	}
	
}
