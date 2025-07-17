package com.utilwed.web.controller.community.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
		int userId = (int) session.getAttribute("userId");
		String nickname = (String) session.getAttribute("loggedInUser");
		
		String title = null;
		String content = null;
		int categoryId = 0;
		int postId = 0;
		
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
					throw new IllegalArgumentException("Unexpected value: " + name);
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

		try {
			boolean updated = postService.updatePost(post, newFileParts, existingFiles);
			if(updated) {
				response.sendRedirect("/category/list/post?c=" + categoryId + "&po=" + postId);
			} else {
				response.sendRedirect("/category/list/post");
			}
		} catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("error", "파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            request.setAttribute("error", "업로드 파일 크기가 너무 크거나 요청이 잘못되었습니다: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "알 수 없는 오류가 발생했습니다: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
		
		
		
		
	}
	
	
	
	
	

}
