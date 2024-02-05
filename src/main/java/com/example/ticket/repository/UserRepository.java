package com.example.ticket.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ticket.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
	
	 User findByUserId(String userId);
	 
	 User findByUserName(String userName);
	 
	 List<User>findByRole(String role);
}
