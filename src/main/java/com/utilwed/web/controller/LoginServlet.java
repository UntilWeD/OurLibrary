package com.utilwed.web.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.Entity.User;
import com.utilwed.web.repository.UserRepository;
import com.utilwed.web.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	private UserService userService; // 서블릿 내에서 UserService 인스턴스 유지
	
	@Override
	public void init() throws ServletException {
		UserRepository userRepository = new UserRepository();
		this.userService = new UserService(userRepository);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = userService.login(username, password);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", user);
			response.sendRedirect("/");
			
		} else {
            // 로그인 실패 시, 메시지를 request에 담아 index.jsp로 포워딩 (브라우저 주소창 유지)
            request.setAttribute("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
            request.getRequestDispatcher("/WEB-INF/view/main/index.jsp").forward(request, response);
		}
		
		
	}
	
	
}
