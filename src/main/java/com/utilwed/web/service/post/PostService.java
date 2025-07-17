package com.utilwed.web.service.post;



import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
	
	
	/**
	 * 포스트를 업데이트 하는 메서드이다.
	 * 1. 새로운 파일 오리지널 이름 리스트와 DB에서 불러온 오리지널 이름 리스트와 비교한 후 삭제해야할 파일과 추가해야할 파일을 걸러낸다.
	 * 2. 걸러낸 파일 리스트를 DB에 먼저 저장한다.
	 * 3. 후에 로컬에 저장된 파일들에 따라 추가할 파일들을 추가한다. 후에 삭제할 파일들을 삭제한다.
	 * 4. 포스트 내용을 업데이트 한다.
	 * @param post, fileParts, existingFiles (기존 첨부파일 id 리스트)
	 * @return boolean 
	 * @throws Exception
	 */
	public boolean updatePost(Post post, Collection<Part> fileParts, List<Integer> existingFileIds) throws Exception {


		
		
		// 1. db에서 불러온 파일 이름 리스트
		List<Attachment> attachments = attachmentRepository.getAttachmentsByPostId(post.getId());
		List<Integer> dbAttachmentFileIds = new ArrayList<Integer>();
		for(Attachment att : attachments) {
			dbAttachmentFileIds.add(att.getId());
		}
		
		// 2. 포스트에서 리스트에 변화가 생겼는지 값을 체크
		List<Integer> deletedAttachmentIds = new ArrayList<Integer>();
		for(int id : dbAttachmentFileIds) {
			if(!existingFileIds.contains(id)) {
				deletedAttachmentIds.add(id);
			}
		}
		
		
		
		// 3. 삭제되어야할 파일 deletedAttachments && 추가해야할 파일들 AddedAttachments
		// 삭제 
		// DB 데이터 && 물리 파일 삭제
		for(Attachment att : attachments) {
			if(deletedAttachmentIds.contains(att.getId())) {
				// db에서 첨부파일 정보 삭제
				attachmentRepository.deleteAttachmentById(att.getId());
				
				// 물리 파일 삭제
				deleteSingleFile(att);
			}
		}
		
		// 로컬에 물리 파일 추가 & DB에 데이터 저장
		String fileSaveRelativePath = File.separator + "post" + File.separator + post.getId(); 
		String fileSaveAbsoulutePath = BASE_UPLOAD_DIR + fileSaveRelativePath;
		
		File uploadDir = new File(fileSaveAbsoulutePath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs(); 
		}
		
		for(Part p : fileParts) {
			String originalFilename = p.getSubmittedFileName();
			if(originalFilename != null && !originalFilename.isEmpty() && p.getSize() > 0) { 
				
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
				attachment.setPostId(post.getId());
				attachment.setOriginalFilename(originalFilename);
				attachment.setUniqueFilename(uniqueFilename);
				attachment.setFilePath(fileSaveRelativePath + File.separator + uniqueFilename); // DB에 저장되는 저장 파일의 경로는 상대경로만
				attachment.setFileSize(p.getSize());
				
				attachmentRepository.insertAttachment(attachment);
			}			
		}
		
		
		
		return postRepository.updatePost(post);
	}

	public int deletePost(int postId) throws Exception {
		
		// 1. 삭제할 포스트 첨부파일 정보 조회
		List<Attachment> attachments = attachmentRepository.getAttachmentsByPostId(postId);
		
		
		// 2. DB에서 첨부파일 정보 삭제를 먼저 해야 하는가?
		// 우선 AI는 ON DELETE CASCADE가 설정되어 있어도 명시적으로 지우는 것이 안전하고 트랜잭션 관리에 유리하다.
		// 후에 attachment를 지우는 메서드 추가
		
		// 3. 포스트 삭제
		int rowsEffected = postRepository.deletePost(postId);
		
		// 4. 물리적인 파일 및 폴더 삭제
		String postFolderPath = BASE_UPLOAD_DIR + File.separator + "post" + File.separator + postId;
		File postDir = new File(postFolderPath);
		
		if(postDir.exists() && postDir.isDirectory()) {
			deleteDirectory(postDir);
			System.out.println("게시물 폴더 삭제 완료 " + postFolderPath);
			
		} else {
			System.out.println("삭제할 게시물 폴더가 없거나 유효하지 않습니다.: " + postFolderPath);
		}
		
		
		return rowsEffected;
	}
	
	private boolean deleteDirectory(File directory) {
		if(!directory.exists()) {
			return true; // 디렉토리가 없으면 이미 삭제된 것으로 간주
		}
		
		if(!directory.isDirectory()) {
			return false; // 디렉터리가 아닐시에
		}
		
		File[] files = directory.listFiles();
		if(files != null) {
			for(File file : files) {
				if(file.isDirectory()) {
					deleteDirectory(directory);
				} else {
					file.delete(); // 파일 삭제
				}
			}
		}
		return directory.delete();
	}
	
	private boolean deleteSingleFile(Attachment attachment) {
		String postFolderPath = BASE_UPLOAD_DIR + attachment.getFilePath();
		File post = new File(postFolderPath);
		
		if(post.exists() && !post.isDirectory()) {
			boolean deleted = post.delete();
			if(deleted) {
				deleted = true;
				System.out.println("첨부파일 삭제 완료 " + postFolderPath);
				return deleted;
			} else {
				System.out.println("삭제할 게시물 폴더가 없거나 유효하지 않습니다.: " + postFolderPath);
			}
		} else {
            System.out.println("삭제할 파일이 없거나 유효하지 않습니다: " + postFolderPath);
        }
		return false;
	}
	
	public void updateView(int postId) {
		postRepository.updatePostViewCount(postId);
	}
	
}
