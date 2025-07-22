package com.utilwed.web.service;

import java.sql.SQLException;
import java.util.List;

import com.utilwed.web.Entity.community.Category;
import com.utilwed.web.repository.CategoryRepository;

public class CategoryService {
	
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> getCategoryListByQuery(String query) throws SQLException{
		return categoryRepository.findCategoriesByQuery(query);
	}

	public List<Category> getBigCategoryList() throws SQLException{
		return categoryRepository.findBigCategories();
	}
	
	

}
