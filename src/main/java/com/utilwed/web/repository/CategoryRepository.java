package com.utilwed.web.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
}
