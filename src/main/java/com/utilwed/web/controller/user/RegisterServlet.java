package com.utilwed.web.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utilwed.web.repository.UserRepository;
import com.utilwed.web.service.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	private UserService userService; // 서블릿 내에서 UserService 인스턴스 유지
	
	@Override
	public void init() throws ServletException {
		UserRepository userRepository = new UserRepository();
		this.userService = new UserService(userRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/main/register.jsp").forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("pwd");
			String email = request.getParameter("email");
			String nickname = request.getParameter("nickname");
			
			int result = userService.registerUser(username, password, email, nickname);
			
			if(result > 0) {
				request.getRequestDispatcher("/WEB-INF/view/main/index.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "해당 아이디는 이미 존재하는 ID입니다.");	
				request.getRequestDispatcher("/WEB-INF/view/main/index.jsp").forward(request, response);
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
