package com.utilwed.web.service;

import java.sql.SQLException;

import com.utilwed.web.Entity.User;
import com.utilwed.web.repository.UserRepository;

public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public int login(String username, String password) throws SQLException {
		int userId = userRepository.findUserIdByUsernameAndPassword(username, password);
		if(userId != 0) {
			return userId;
		}
		return 0;
	}

	public int registerUser(String username, String password, String email, String nickname) throws SQLException {	
		if(userRepository.saveUser(username, password, email, nickname)) {
			return 1;
		}
		return 0; // -> 변화없음, 즉 이미 존재하는 사용자
	}
	
	public User getUserByName(String username) throws SQLException {
		User resultUser = userRepository.findUserByUserName(username);
		return resultUser;
	}
	
	public boolean updateUser(int userId, User user) throws SQLException {
		if(userRepository.updateUser(userId, user)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteUser(int userId, String password) throws SQLException {
		if(userRepository.deleteUser(userId, password))
			return true;
		return false;
	}
	
	

}
