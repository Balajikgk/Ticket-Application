package com.example.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticket.helper.Helper;
import com.example.ticket.model.User;
import com.example.ticket.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Helper helper;
	
	public User createUser(User user,String loggedInUser) {
		user.setUpdatedInfo(helper.updateInfo(loggedInUser));
		return userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	public User getUserById(String userId) {
		return userRepository.findByUserId(userId);
	}

}
