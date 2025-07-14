package com.utilwed.web.Entity.community;

import java.util.Date;

public class Comment {
	private int id;
	private String content;
	private Date createdAt;
	private Date updatedAt;
	
	private int userId;
	private int postId;
	
	private String username;
	
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", userId=" + userId + ", postId=" + postId + "]";
	}

	public Comment() {
	}

	public Comment(int id, String content, Date createdAt, Date updatedAt, int userId, int postId) {
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
	
	

	public Comment(int id, String content, Date createdAt, int userId, int postId, String username) {
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.userId = userId;
		this.postId = postId;
		this.username = username;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	
	
	
	
}
