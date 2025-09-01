package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.User;
import com.example.repo.UserRepo;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	UserRepo repo;
	
	@GetMapping("{userId}")
	public User getUserById(@PathVariable("userId") Integer userId)
	{
		//
		return service.getUserById(userId);
		
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers()
	{
		return (List<User>) repo.findAll();
	}
	
}
