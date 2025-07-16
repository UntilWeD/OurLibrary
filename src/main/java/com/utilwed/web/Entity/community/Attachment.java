package com.utilwed.web.Entity.community;

import java.util.Date;

public class Attachment {
	private int id;
	private int postId;
	private String originalFilename;
	private String uniqueFilename;
	private String filePath; // 서버의 상대 경로 
	private long fileSize;
	private Date uploadDate;
	
	
	public Attachment() {
	}


	public Attachment(int id, int postId, String originalFilename, String uniqueFilename, String filePath,
			long fileSize, Date uploadDate) {
		this.id = id;
		this.postId = postId;
		this.originalFilename = originalFilename;
		this.uniqueFilename = uniqueFilename;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.uploadDate = uploadDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public String getOriginalFilename() {
		return originalFilename;
	}


	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}


	public String getUniqueFilename() {
		return uniqueFilename;
	}


	public void setUniqueFilename(String uniqueFilename) {
		this.uniqueFilename = uniqueFilename;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public long getFileSize() {
		return fileSize;
	}


	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	@Override
	public String toString() {
		return "Attachment [id=" + id + ", postId=" + postId + ", originalFilename=" + originalFilename
				+ ", uniqueFilename=" + uniqueFilename + ", filePath=" + filePath + ", fileSize=" + fileSize
				+ ", uploadDate=" + uploadDate + "]";
	}
	
	
	
	
	
	
	
}
