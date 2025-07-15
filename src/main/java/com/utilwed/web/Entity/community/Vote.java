package com.utilwed.web.Entity.community;

import java.util.Date;

public class Vote {
	int id;
	VoteType voteType;
	int userId;
	int postId;
	Date createdAt;
	
	public Vote() {
	}

	public Vote(int id, VoteType voteType, int userId, int postId, Date createdAt) {
		this.id = id;
		this.voteType = voteType;
		this.userId = userId;
		this.postId = postId;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
}
