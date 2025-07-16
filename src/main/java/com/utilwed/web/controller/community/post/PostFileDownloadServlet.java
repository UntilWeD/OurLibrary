package com.utilwed.web.controller.community.post;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.Entity.community.Attachment;
import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.service.post.PostService;

@WebServlet("/category/list/post/file/download")
public class PostFileDownloadServlet extends HttpServlet{
	
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		PostRepository postRepository = new PostRepository();
		AttachmentRepository attachmentRepository = new AttachmentRepository();
		this.postService = new PostService(postRepository, attachmentRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uniqueFilename = request.getParameter("filename");
		
		try {
			Attachment attachment = postService.getAttachmentByUniqueFilename(uniqueFilename);
			String filePathOnDisk = postService.getActualFilePathForDownload(uniqueFilename);
			
			
			File file = new File(filePathOnDisk);
			if (!file.exists() || !file.isFile()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일 시스템에서 파일을 찾을 수 없습니다.");
                return;
            }
			
			// 응답헤더 설정
			response.setContentType("application/octet-stream"); // 일반 이진 파일
			
			// 한글 파일명 깨짐 방지를 위해 원본 파일명을 URLENCODER.enocode
			String originalFilename = attachment.getOriginalFilename();
			String encodedOriginalName = URLEncoder.encode(originalFilename, "UTF-8").replaceAll("\\+", "%20");
			
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedOriginalName + "\"");
            response.setHeader("Content-Length", String.valueOf(file.length()));
            
            // 파일 스트림 전송
            try (FileInputStream fis = new FileInputStream(file);
            	OutputStream os = response.getOutputStream()){
            	byte[] buffer = new byte[4096]; // 4kb 버퍼
            	int bytesRead;
            	while((bytesRead = fis.read(buffer)) != -1) {
            		os.write(buffer, 0, bytesRead);
            	}
            	os.flush();
            }	
			
            
		} catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터베이스 오류로 파일을 다운로드할 수 없습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "파일 다운로드 중 알 수 없는 오류 발생: " + e.getMessage());
        }
	}
	
}
