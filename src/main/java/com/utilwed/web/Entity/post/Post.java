package com.utilwed.web.Entity.post;

import java.time.LocalDateTime;
import java.util.Date;

public class Post {
	private int id;
	private String title;
	private String content;
	private String nickname;
	private Date createdAt;
	private Date updatedAt;
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


	public Post(int id, String title, String content, String nickname, Date createdAt, Date updatedAt, int likeCount,
			int dislikeCount, int view, int userId, int categoryId) {
		this.id = id;
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
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
