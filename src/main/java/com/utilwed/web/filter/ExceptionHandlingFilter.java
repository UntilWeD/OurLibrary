package com.utilwed.web.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class ExceptionHandlingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HttpServletRequest req =  (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			
			req.setAttribute("errorMessage", e.getMessage());
			req.getRequestDispatcher("WEB-INF/view/error.jsp").forward(req, res);
			
			
		}
		
	}
	
}
