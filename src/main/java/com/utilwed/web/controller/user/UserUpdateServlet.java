package com.utilwed.web.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.Entity.User;
import com.utilwed.web.repository.UserRepository;
import com.utilwed.web.service.UserService;

@WebServlet("/user/update")
@MultipartConfig
public class UserUpdateServlet extends HttpServlet {
	
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		UserRepository userRepository = new UserRepository();
		this.userService = new UserService(userRepository);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession(false);
			
			int userId = (int) session.getAttribute("userId");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String nickname = request.getParameter("nickname");
			
			
			User user = new User(username, password, email, nickname);
			
		    userService.updateUser(userId, user);
		    session.setAttribute("loggedInUser", username);
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
