package com.utilwed.web.service;

import java.util.Optional;

import com.utilwed.web.Entity.User;
import com.utilwed.web.repository.UserRepository;

public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User login(String username, String password) {
		Optional<User> user = Optional.of(userRepository.findUserByUserName(username));
		
		if(user.isPresent()) {
			return user.get();
		}
		
		
		return null;
	}

	public int registerUser(String username, String password) {	
		if(userRepository.saveUser(username, password)) {
			return 1;
		}
		
		return 0; // -> 변화없음, 즉 이미 존재하는 사용자
	}

}
