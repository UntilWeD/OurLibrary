package com.utilwed.web.controller.community.post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.Entity.community.Comment;
import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.CategoryRepository;
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
		BaseRepository baseRepository = new BaseRepository();
		PostRepository postRepository = new PostRepository();
		AttachmentRepository attachmentRepository = new AttachmentRepository();
		CategoryRepository categoryRepository = new CategoryRepository();
		this.postService = new PostService(postRepository, attachmentRepository, baseRepository, categoryRepository);
		// 싱글톤 객체여도 상태를 저장하는 행동은 없고
		// getConnection으로 새로운 커넥션을 반환하는 것이기에 동시성에서 문제가 될만한 건 없다.
		CommentRepository commentRepository = new CommentRepository();
		this.commentService = new CommentService(commentRepository, postRepository, baseRepository);
	}
	
	// 트랜잭션 적용 X
	// 간단한 포스트 조회에서는 트랜잭션을 사용하지 않는다. 
	// updateView가 있지만 비즈니스적으로 크게 문제가 되지않고 
	// 조회자체는 데이터를 변경하지 않기 때문에 트랜잭션을 적용하는 것은 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
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
			
			
			request.getRequestDispatcher("/WEB-INF/view/community/post/post.jsp")
			.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": DB와 관련하여 오류가 발생하였습니다. -> " + e.getMessage());
		} catch (NullPointerException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있습니다. -> " + e.getMessage());
		} catch (NumberFormatException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있거나 숫자변환 중 오류가 발생하였습니다. -> "+ e.getMessage());
		} catch (Exception e) { 
			throw new ServletException(this.getClass().getSimpleName() + ": 알 수 없는 오류가 발생하였습니다. -> "+ e.getMessage());
		}
		
		
	}
	
}
