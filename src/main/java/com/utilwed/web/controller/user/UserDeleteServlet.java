package com.utilwed.web.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilwed.web.repository.UserRepository;
import com.utilwed.web.service.UserService;

@WebServlet("/user/delete")
public class UserDeleteServlet extends HttpServlet {
	
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		UserRepository userRepository = new UserRepository();
		this.userService = new UserService(userRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/user/userDeleteForm.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		System.out.println(password);
		
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");
		
		boolean deleted = userService.deleteUser(userId, password);
		
		if(deleted) {
			response.sendRedirect("/");
			session.invalidate();
			System.out.println("사용자 세션이 삭제되었습니다.");
		} else {
			request.setAttribute("error", "비밀번호가 틀렸습니다.");
		    request.getRequestDispatcher("/WEB-INF/view/user/userDeleteForm.jsp").forward(request, response);
		}
		
	}

}
