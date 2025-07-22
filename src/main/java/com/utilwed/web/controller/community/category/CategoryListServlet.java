package com.utilwed.web.controller.community.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.utilwed.web.Entity.community.Category;
import com.utilwed.web.repository.CategoryRepository;
import com.utilwed.web.service.CategoryService;

@WebServlet("/category")
public class CategoryListServlet extends HttpServlet{
	
	private CategoryService categoryService;
	
	@Override
	public void init() throws ServletException {
		CategoryRepository categoryRepository = new CategoryRepository();
		this.categoryService = new CategoryService(categoryRepository);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// 1. 카테고리 리스트 가져오기
			List<Category> bigCategoryList = categoryService.getBigCategoryList();
			request.setAttribute("bigCategoryList", bigCategoryList);
			
			request.getRequestDispatcher("/WEB-INF/view/community/categoryList.jsp")
			.forward(request, response);			
		}  catch (NullPointerException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있습니다. -> " + e.getMessage());
		} catch (NumberFormatException e) {
			throw new ServletException(this.getClass().getSimpleName() + ": 입력값이 비어있거나 숫자변환 중 오류가 발생하였습니다. -> "+ e.getMessage());
		} catch (Exception e) { 
			throw new ServletException(this.getClass().getSimpleName() + ": 알 수 없는 오류가 발생하였습니다. -> "+ e.getMessage());
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try {
			String query_ =  request.getParameter("query");
			
			String query = "";
			if(query_ != null && !query_.equals(""))
				query = query_;
			
			List<Category> foundedCategories = categoryService.getCategoryListByQuery(query);
			
				
			Gson gson = new Gson();
			
			String json = gson.toJson(foundedCategories);
			System.out.println(json);
			
			response.getWriter().write(json);
			
			
		} catch (Exception e) {
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("{\"error\": \"서버 오류\"}");
	    }
	}
	
	
}
