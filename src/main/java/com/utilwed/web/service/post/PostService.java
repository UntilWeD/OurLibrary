package com.utilwed.web.service.post;



import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import com.utilwed.web.Entity.community.Attachment;
import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.AttachmentRepository;
import com.utilwed.web.repository.PostRepository;

public class PostService {

	private PostRepository postRepository;
	private AttachmentRepository attachmentRepository;

	
	
	public PostService(PostRepository postRepository, AttachmentRepository attachmentRepository) {
		this.postRepository = postRepository;
		this.attachmentRepository = attachmentRepository;
	}

	private final String BASE_UPLOAD_DIR = "C:/WORKSHOP/Projects/EclipseWorkSpace/OurLibrary";
	
	public int savePost(Post post, Collection<Part> fileParts, int categoryId) throws Exception{
		// 1. 게시물을 먼저 db에 삽입하고 생성된 ID를 받아옴
		int postId = postRepository.savePost(post);
		if(postId <= 0) {
			throw new RuntimeException("게시물 등록에 실패했습니다 - ID 반환 실패");
		}
		post.setId(postId);
		
		// 2. 파일 저장 경로 생성
		String fileSaveRelativePath = File.separator + "post" + File.separator + postId; 
		String fileSaveAbsoulutePath = BASE_UPLOAD_DIR + fileSaveRelativePath;
		
		File uploadDir = new File(fileSaveAbsoulutePath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs(); // 디렉토리가 없으면 생성
		}
		
		// 3. 첨부 파일 처리 및 DB 저장
		for(Part p : fileParts) {
			// isFormFiled()가 false이고, 파일명이 버이있지 않으며, 파일 크기가 0보다 커야 실제파일
			String originalFilename = p.getSubmittedFileName();
			if(originalFilename != null && !originalFilename.isEmpty() && p.getSize() > 0) { // 파일 part인지 확인
				
				String fileExtension = "";
				int dotIndex = originalFilename.lastIndexOf('.');
				if(dotIndex >0 && dotIndex < originalFilename.length() -1) {
					fileExtension = originalFilename.substring(dotIndex);
				}
				String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
				String filePathOnDisk = fileSaveAbsoulutePath + File.separator + uniqueFilename;
				
				try {
					p.write(filePathOnDisk);
				} catch (IOException e) {
					// TODO: handle exception
                    System.err.println("파일 저장 실패: " + originalFilename + " - " + e.getMessage());
                    throw new IOException("파일 저장 중 오류 발생", e);
				}
				
				Attachment attachment = new Attachment();
				attachment.setPostId(postId);
				attachment.setOriginalFilename(originalFilename);
				attachment.setUniqueFilename(uniqueFilename);
				attachment.setFilePath(fileSaveRelativePath + File.separator + uniqueFilename); // DB에 저장되는 저장 파일의 경로는 상대경로만
				attachment.setFileSize(p.getSize());
				
				attachmentRepository.insertAttachment(attachment);
			}			
		}
		
		return postId;
	}
	
	
	
	public List<Post> getPostList(int categoryId, String field, String query, int page){
		return postRepository.getPostList(categoryId, field, query, page);
	}
	
	public int getPostCount(String field, String query, int categoryId) {
		return postRepository.getPostCount(categoryId, field, query);
	}
	
	public Post getPost(int categoryId, int postId) throws SQLException {
		Post post = postRepository.getPost(categoryId, postId);
		if(post != null) {
			List<Attachment> attachments = attachmentRepository.getAttachmentsByPostId(postId);
			post.setAttachments(attachments);
		}
		return post;
	}
	
	// 다운로드 서블릿 용
	public Attachment getAttachmentByUniqueFilename(String uniqueFilename) throws SQLException{
		return attachmentRepository.getAttachmentByUniqueFilename(uniqueFilename);
	}
	
	// 파일 다운로드 시 실제 파일의 절대 경로를 반환
	public String getActualFilePathForDownload(String uniqueFilenname) throws SQLException{
		Attachment attachment = attachmentRepository.getAttachmentByUniqueFilename(uniqueFilenname);
		if(attachment != null) {
			return BASE_UPLOAD_DIR + attachment.getFilePath();
		}
		return null;
	}
	
	public boolean updatePost(Post post) {
		return postRepository.updatePost(post);
	}

	public int deletePost(int postId) {
		return postRepository.deletePost(postId);
	}
	
	public void updateView(int postId) {
		postRepository.updatePostViewCount(postId);
	}
	
}
