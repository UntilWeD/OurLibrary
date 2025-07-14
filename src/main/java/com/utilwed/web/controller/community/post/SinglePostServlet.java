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
		CommentRepository commentRepository = new CommentRepository();
		this.commentService = new CommentService(commentRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int categoryId = Integer.parseInt(request.getParameter("c"));
		int postId = Integer.parseInt(request.getParameter("po"));
		
		// 포스트 가져오기
		Post post = postService.getPost(categoryId, postId);
		request.setAttribute("po", post);
		
		// 댓글 가져오기
		List<Comment> commentList = commentService.getCommentList(postId);
		request.setAttribute("commentList", commentList);
		
		// 좋아요 가져오기
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/community/post/post.jsp")
		.forward(request, response);
		
		
	}
	
}
