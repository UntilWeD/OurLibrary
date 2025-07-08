package com.utilwed.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.Entity.User;
import com.utilwed.web.repository.UserRepository;
import com.utilwed.web.service.UserService;

@WebServlet("/user/profile")
public class UserProfileServlet extends HttpServlet{
	
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		UserRepository userRepository = new UserRepository();
		this.userService = new UserService(userRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		// 1. 세션 유효성 및 로그인 여부 확인
		if(session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("/");
			return;
		}
		
		String loggedInUsername = (String) session.getAttribute("loggedInUser");
		User loggedInUserDetail = userService.getUserByName(loggedInUsername);
		
		request.setAttribute("u", loggedInUserDetail);
		
		if(loggedInUserDetail != null) {
			request.getRequestDispatcher("/WEB-INF/view/main/user/userPage.jsp").forward(request, response);
		} else {
			session.invalidate();
			response.sendRedirect("/ErrorPage");
		}	
		
		
	}
	

}
