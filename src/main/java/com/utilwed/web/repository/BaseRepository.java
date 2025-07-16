package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BaseRepository {
	
	protected String jdbcUrl = "jdbc:mysql://localhost:3306/ourlibrary?useSSL=false&serverTimezone=UTC";
	protected String dbUser = "root";
	protected String dbPassword = "1234";
	
	public BaseRepository() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC 드라이버 로드 실패", e);
        }
	}
	
	protected Connection getConnection() throws SQLException{
		return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
	}

}
