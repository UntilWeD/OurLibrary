package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utilwed.web.Entity.community.Category;


public class CategoryRepository extends BaseRepository{

	public Category getCategoryNameById(int id) throws SQLException {
		String sql = "SELECT * FROM category WHERE id = ?";
		Category category = null;
		
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);){
			
			st.setInt(1, id);
			
			try(ResultSet rs = st.executeQuery();){
				if (rs.next()) {
					int resultId = rs.getInt("id");
					int parentId = rs.getInt("parent_id");
					String name = rs.getString("name");
					
					category = new Category(
						resultId,
						parentId,
						name
					);
					
				}
			}
		} 
		return category;
		
	}

	public List<Category> findCategoriesByQuery(String query) throws SQLException{
		String sql = "SELECT * FROM category WHERE name LIKE ?";
		List<Category> result = new ArrayList<Category>();
		
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setString(1, "%" + query + "%");

				
				try(ResultSet rs = pstmt.executeQuery();){
					while(rs.next()) {
						int id = rs.getInt("id");
						int parentId = rs.getInt("parent_id");
						String name = rs.getString("name");
						
						Category category = new Category(
								id,
								parentId,
								name
								);
						
						result.add(category);
						
					}
				}

			}
			
			return result;
	}

	public List<Category> findBigCategories() throws SQLException{
		String sql = "SELECT * FROM category WHERE parent_id IS NULL";
		
		List<Category> result = new ArrayList<Category>();
		
		try (Connection con = getConnection();
			Statement stmt = con.createStatement();){
				
				try(ResultSet rs = stmt.executeQuery(sql);){
					while(rs.next()) {
						int id = rs.getInt("id");
						int parentId = rs.getInt("parent_id");
						String name = rs.getString("name");
						
						Category category = new Category(
								id,
								parentId,
								name
						);
						
						result.add(category);
						
					}
				}

			}
			
			return result;
	}
	
	
}
