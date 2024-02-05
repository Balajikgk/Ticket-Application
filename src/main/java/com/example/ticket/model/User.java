package com.example.ticket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class User {

	@Id
	private String userId;
	private String userName;
	private Role role;
	private UpdatedInfo updatedInfo;

}
