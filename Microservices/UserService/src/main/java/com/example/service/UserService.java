package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.beans.User;
import com.example.repo.UserRepo;

@Service
public class UserService {
	
	
	@Autowired
	UserRepo repo;
	
//	@Autowired
//	RestTemplate restTemplate;
	

	
	
	public User getUserById(Integer userId)
	{
		
		User user = repo.findById(userId).get();
		return user;
	}
	

}
