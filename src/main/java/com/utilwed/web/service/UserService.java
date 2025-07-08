package com.utilwed.web.service;

import java.util.Optional;

import com.utilwed.web.Entity.User;
import com.utilwed.web.repository.UserRepository;

public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public int login(String username, String password) {
		int userId = userRepository.findByUsernameAndPassword(username, password);
		if(userId != 0) {
			return userId;
		}
		
		return 0;
	}

	public int registerUser(String username, String password, String email, String nickname) {	
		if(userRepository.saveUser(username, password, email, nickname)) {
			return 1;
		}
		
		return 0; // -> 변화없음, 즉 이미 존재하는 사용자
	}
	
	public User getUserByName(String username) {
		
		User resultUser = userRepository.findUserByUserName(username);
		
		return resultUser;
	}
	
	public boolean updateUser(int userId, User user) {
		if(userRepository.updateUser(userId, user)) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
