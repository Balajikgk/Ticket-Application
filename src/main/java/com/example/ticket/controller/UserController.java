package com.example.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket.model.User;
import com.example.ticket.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping(path = "/create")
	public User createUser(@RequestBody User user,@RequestHeader("loggedInUser")String loggedInUser) {
		return service.createUser(user,loggedInUser);
	}
	
	@GetMapping(path = "/getAllUser")
	public List<User> getAllUser() {
		return service.getAllUser();
	}
	
	@GetMapping(path = "/getByUser/{userId}")
	public User getUserById(@PathVariable String userId) {
		return service.getUserById(userId);
	}

}
