package com.utilwed.web.controller.community.vote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.utilwed.web.Entity.community.VoteType;
import com.utilwed.web.repository.BaseRepository;
import com.utilwed.web.repository.PostRepository;
import com.utilwed.web.repository.VoteRepository;
import com.utilwed.web.service.VoteService;

@WebServlet("/category/list/post/vote")
public class SaveVoteServlet extends HttpServlet{
	
	private VoteService voteService;
	private Gson gson = new Gson(); // JSON 처리 라이브러리
	
	@Override
	public void init() throws ServletException {
		VoteRepository voteRepository = new VoteRepository();
		PostRepository postRepository = new PostRepository();
		BaseRepository baseRepository = new BaseRepository();
		this.voteService = new VoteService(voteRepository,postRepository, baseRepository);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		int userId = (int)session.getAttribute("userId");
		
		//1. 요청 본문 (JSON) 읽기
		StringBuilder sb = new StringBuilder(); 
		BufferedReader reader = request.getReader();
		String line;
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String requestBody = sb.toString();
		
		//2.JSON 파싱
		Map<String, Object> requestData = gson.fromJson(requestBody, HashMap.class);
		int postId = ((Double) requestData.get("postId")).intValue(); // JSON 숫자는 기본적으로 Double로 파싱됨
		String voteTypeStr = (String) requestData.get("voteType");
		VoteType voteType = VoteType.valueOf(voteTypeStr);
		
		try {
            // 좋아요 가능 여부 검증 (userId와 postId를 모두 넘겨야 해당 게시물에 대한 검증이 정확)
            // voteService.canUserVoteToday(userId) -> userId만 받는다면, 해당 유저가 '어떤' 게시물이든
            //                            하루 한 번만 투표할 수 있다는 의미가 됨.
            // 여기서는 postId도 함께 넘기는 것이 일반적인 '게시물별 좋아요 제한'에 맞음.

            if (!voteService.canUserVoteToday(userId)) { // canUserVoteToday가 '가능하면 true'라고 가정
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
                out.print(gson.toJson(Map.of("message", "이 게시물에는 하루에 한 번만 좋아요를 누를 수 있습니다.")));
                return;
            }
			int savedVoteId = voteService.saveVote(userId, postId, voteType);
			
			Map<String, Integer> responseMap = voteService.getVoteCount(postId);
			out.print(gson.toJson(responseMap));
		} catch (Exception e) {
			// 5. 실패응답
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);;
			out.print(gson.toJson(Map.of("message", e.getMessage())));
			e.printStackTrace();
		}
	}

}
