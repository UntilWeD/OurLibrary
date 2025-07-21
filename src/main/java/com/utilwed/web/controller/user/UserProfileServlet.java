package com.utilwed.web.controller.user;

import java.io.IOException;
import java.sql.SQLException;

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
		
		try {
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
				request.getRequestDispatcher("/WEB-INF/view/user/userPage.jsp").forward(request, response);
			} else {
				session.invalidate();
				request.setAttribute("errorMessage", "정상적으로 사용자 계정을 불러오지 못하였습니다.");
			    request.getRequestDispatcher("/WEB-INF/view/user/userDeleteForm.jsp").forward(request, response);
			}	
			
		} catch (SQLException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": DB와 관련하여 오류가 발생하였습니다. -> " + e.getMessage());
		} catch (NullPointerException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있습니다. -> " + e.getMessage());
		} catch (NumberFormatException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있거나 숫자변환 중 오류가 발생하였습니다. -> "+ e.getMessage());
		} catch (Exception e) { 
			throw new ServletException(this.getClass().getSimpleName() + ": 알 수 없는 오류가 발생하였습니다. -> "+ e.getMessage());
		}
		
		
	}
	

}
