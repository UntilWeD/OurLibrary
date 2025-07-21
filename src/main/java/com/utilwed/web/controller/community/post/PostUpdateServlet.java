package com.utilwed.web.controller.community.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.CategoryRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;


@WebServlet("/category/list/post/edit")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50 * 5 // 250MB
	)
public class PostUpdateServlet extends HttpServlet{
	
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
		
		try {
			int categoryId = Integer.parseInt(request.getParameter("c"));
			int postId = Integer.parseInt(request.getParameter("po"));
		
			Post post = postService.getPost(categoryId, postId);
			request.setAttribute("po", post);

			
			request.getRequestDispatcher("/WEB-INF/view/community/post/postForm.jsp")
			.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(this.getClass().getSimpleName() + "DB와 관련하여 오류가 발생하였습니다." + e.getMessage());
		} catch (NullPointerException e) {
			throw new ServletException(this.getClass().getSimpleName() + "입력값이 비어있습니다." + e.getMessage());
		} catch (NumberFormatException e) {
			throw new ServletException(this.getClass().getSimpleName() + "입력값이 비어있거나 숫자변환 중 오류가 발생하였습니다."+ e.getMessage());
		} catch (Exception e) {
			throw new ServletException(this.getClass().getSimpleName() + "알 수 없는 오류가 발생하였습니다."+ e.getMessage());
		}
	}	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = null;
		String content = null;
		int categoryId = 0;
		int postId = 0;
		try {
			HttpSession session = request.getSession(false);
			int userId = (int) session.getAttribute("userId");
			String nickname = (String) session.getAttribute("loggedInUser");
			
			
			List<Part> newFileParts = new ArrayList<Part>();
			
			// 기존에 존재하던 첨부파일 리스트 
			List<Integer> existingFiles = new ArrayList<Integer>();
			
			
			// 1. 폼 필드 파싱 (Part 객체에서 값 가져오기)
			Collection<Part> parts = request.getParts();
			for(Part p : parts) {
				String name = p.getName();
				
				// 파일명이 null이 아니거나 비어있지 않으면 파일 필드로 간주
				if(p.getSubmittedFileName() != null && !p.getSubmittedFileName().isEmpty()) {
					newFileParts.add(p); // 파일 p는 리스트에 추가
				} else {
					// 일반 폼 필드인 경우 (파일명이 없거나 비어 있음)
					String value;
					try(BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))){
						value = reader.lines().collect(Collectors.joining("\n"));				
					} catch (IOException e) {
						throw new ServletException("폼 필드 값을 읽는 중 오류 발생 " + name, e);
					}
					
					switch (name) {
					case "title" : title = value; break;
					case "content" : content = value; break;
					case "categoryId" : categoryId = Integer.parseInt(value); break;
					case "existingAttachmentIds" : existingFiles.add(Integer.parseInt(value)); break;
					case "postId" : postId = Integer.parseInt(value); break;
					case "file": break;
					default: 
					}
				}
			}
			
			Post post = new Post(
					postId,
					title,
					content,
					nickname,
					userId,
					categoryId
			);

			response.setContentType("application/json");
			
			boolean updated = postService.updatePost(post, newFileParts, existingFiles);
			
			if(updated) {
				response.getWriter().write("{\"redirectUrl\": \"/category/list/post?c=" + categoryId + "&po=" + postId + "\"}");
			} else {
				response.getWriter().write("{\"redirectUrl\": \"/category/list/post?c=" + categoryId + "&po=" + postId + "\"}, "
						+ "\"errorMessage\": \"제대로 수정되지 않았습니다. 잠시 후 다시 시도해주세요.\"}");
			}

		} catch (SQLException e) {
			response.getWriter().write("{\"redirectUrl\": \"/category/list/post?c=" + categoryId + "&po=" + postId + "\"}, "
					+ "\"errorMessage\": \"DB 오류가 발생했습니다. 잠시 후 다시 시도해주세요.\"}");
		} catch (IOException e) {
			response.getWriter().write("{\"redirectUrl\": \"/category/list/post?c=" + categoryId + "&po=" + postId + "\"}, "
					+ "\"errorMessage\": \"입력과 관련하여 오류가 발생했습니다. 잠시 후 다시 시도해주세요.\"}");
        } catch (ServletException e) {
			response.getWriter().write("{\"redirectUrl\": \"/category/list/post?c=" + categoryId + "&po=" + postId + "\"}, "
					+ "\"errorMessage\": \"서블릿과 관련하여 오류가 발생했습니다. 잠시 후 다시 시도해주세요.\"}");
        } catch (Exception e) {
			response.getWriter().write("{\"redirectUrl\": \"/category/list/post?c=" + categoryId + "&po=" + postId + "\"}, "
					+ "\"errorMessage\": \"알 수 없는 오류가 발생했습니다. 잠시 후 다시 시도해주세요.\"}");
        } 
		
		
		
	}
	
	
	
	
	

}
