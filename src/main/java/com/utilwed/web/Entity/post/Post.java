package com.utilwed.web.Entity.post;

import java.time.LocalDateTime;

public class Post {
	private String title;
	private String content;
	private String nickname;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int likeCount;
	private int dislikeCount;
	private int view;
	
	private int userId;
	private int categoryId;
	
	
	
	public Post() {
	}
	

	public Post(String title, String content, String nickname, int userId, int categoryId) {
		super();
		this.title = title;
		this.content = content;
		this.nickname = nickname;
		this.userId = userId;
		this.categoryId = categoryId;
	}


	public Post(String title, String content, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt,
			int likeCount, int dislikeCount, int view, int userId, int categoryId) {
		this.title = title;
		this.content = content;
		this.nickname = nickname;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.view = view;
		this.userId = userId;
		this.categoryId = categoryId;
	}
	
	
	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", nickname=" + nickname + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", likeCount=" + likeCount + ", dislikeCount=" + dislikeCount + ", view="
				+ view + ", userId=" + userId + ", categoryId=" + categoryId + "]";
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
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


	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public int getDislikeCount() {
		return dislikeCount;
	}


	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}


	public int getView() {
		return view;
	}


	public void setView(int view) {
		this.view = view;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	
	
	
	
	
	
}
