package com.atcdilivery.spring.jwt.mongodb.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class  JwtResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;

	private String firstName;

	private String lastName;

	private String userId;
	private List<String> roles;

	public JwtResponse(String firstName,String lastName,String accessToken, String id, String username, String email, List<String> roles,String userId) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
	}
}
