package com.utilwed.web.Entity;


public class User {
	
	
	private String username;
	private String password;
	private String email;
	private String nickname;
	
	
	
	
	public User() {
		
	}




	public User(String username, String password, String email, String nickname) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
	}
	
	


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", nickname=" + nickname
				+ "]";
	}




	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
	
	
	
}
