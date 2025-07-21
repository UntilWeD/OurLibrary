package com.utilwed.web.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import com.utilwed.web.Entity.community.VoteType;
import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.repository.VoteRepository;

public class VoteService {

	private VoteRepository voteRepository;
	private PostRepository postRepository;
	private BaseRepository baseRepository;

	
	
	public VoteService(VoteRepository voteRepository, PostRepository postRepository, BaseRepository baseRepository) {
		this.voteRepository = voteRepository;
		this.postRepository = postRepository;
		this.baseRepository = baseRepository;
	}

	public int saveVote(int userId,int postId, VoteType voteType) throws SQLException {
		Connection conn = null;
		int savedVoteId = -1;
		
		try {
			conn = baseRepository.getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작
			
			// 1. 투표 정보 업데이트
			if(voteType.name().equals("LIKE"))
				postRepository.incrementLikeCount(postId, conn);
			else
				postRepository.incrementDislikeCount(postId, conn);
			
			savedVoteId = voteRepository.saveVote(userId, postId, voteType, conn);
			
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
			conn.close();
			
		}
		return savedVoteId;
			
	}
	
	public Map<String, Integer> getVoteCount(int postId) throws SQLException{
		return postRepository.getVotesCount(postId);
	}

	public boolean canUserVoteToday(int userId) throws SQLException{
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
