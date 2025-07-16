package com.utilwed.web.Entity.community;

import java.util.Date;
import java.util.List;

public class Post {
	private int id;
	private String title;
	private String content;
	private String nickname;
	private Date createdAt;
	private Date updatedAt;
	private int likeCount;
	private int dislikeCount;
	private int commentCount;
	private int view;
	private List<Attachment> attachments;
	
	
	private int userId;
	private int categoryId;
	
	
	
	public Post() {
	}
	

	// Post 저장시
	public Post(String title, String content, String nickname, int userId, int categoryId) {
		super();
		this.title = title;
		this.content = content;
		this.nickname = nickname;
		this.userId = userId;
		this.categoryId = categoryId;
	}
	
	// Post 수정시
	public Post(int id, String title, String content, String nickname, int userId, int categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.nickname = nickname;
		this.userId = userId;
		this.categoryId = categoryId;
	}
	

	// Post 불러올 시
	public Post(int id, String title, String content, String nickname, Date createdAt, Date updatedAt, int likeCount,
			int dislikeCount, int commentCount, int view, int userId, int categoryId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.nickname = nickname;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.commentCount = commentCount;
		this.view = view;
		this.userId = userId;
		this.categoryId = categoryId;
	}
	
	
	public List<Attachment> getAttachments() {
		return attachments;
	}


	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}


	public int getId() {
		return id;
	}


	public int getCommentCount() {
		return commentCount;
	}


	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
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
