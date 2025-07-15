package com.utilwed.web.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import com.utilwed.web.Entity.community.VoteType;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.repository.VoteRepository;

public class VoteService {

	private VoteRepository voteRepository;
	private PostRepository postRepository;

	public VoteService(VoteRepository voteRepository, PostRepository postRepository) {
		this.voteRepository = voteRepository;
		this.postRepository = postRepository;
	}
	
	public int saveVote(int userId,int postId, VoteType voteType) {
		if(voteType.name() == "LIKE")
			postRepository.incrementLikeCount(postId);
		else
			postRepository.incrementDislikeCount(postId);
		
		return voteRepository.saveVote(userId, postId, voteType);
	}
	
	public Map<String, Integer> getVoteCount(int postId){
		return postRepository.getVotesCount(postId);
	}

	public boolean canUserVoteToday(int userId) {
		Date lastVoteDate = voteRepository.getCreatedAtByUserId(userId);
		// 2. 마지막 투표 기록이 없는 경우: 투표 가능
        if (lastVoteDate == null) {
            return true;
        }
        
        // 3. 마지막 투표 기록이 있는 경우: 오늘 날짜와 비교
        LocalDate today = LocalDate.now(); // 현재 날짜
        LocalDate lastVoteLocalDate = lastVoteDate.toInstant()
        										.atZone(ZoneId.systemDefault()) // 시스템 기본 시간대 사용
        										.toLocalDate(); // 날짜부분만 추출
        
		return !today.isEqual(lastVoteLocalDate);
	}
	
	
	
	
}
