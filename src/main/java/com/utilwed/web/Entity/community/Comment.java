package com.utilwed.web.Entity.community;

import java.time.LocalDateTime;

public class Comment {
	private int id;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private int userId;
	private int postId;
	
	
	
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", userId=" + userId + ", postId=" + postId + "]";
	}

	public Comment() {
	}

	public Comment(int id, String content, LocalDateTime createdAt, LocalDateTime updatedAt, int userId, int postId) {
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userId = userId;
		this.postId = postId;
	}


	public Comment(String content, int userId, int postId) {
		this.content = content;
		this.userId = userId;
		this.postId = postId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	
	
	
	
	
}
