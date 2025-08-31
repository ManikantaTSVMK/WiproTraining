package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@GetMapping("/")
	public String WelcomeMessage()
	{
		return "Welcome to Spring Security Session";
	}
	
	// admin can access 
	@GetMapping("/admin/add")
	public String WelcomeAdminMessage1()
	{
		return "Record added";
	}
	
	// admin can access 
	@GetMapping("/admin/delete")
	public String WelcomeAdminMessage2()
	{
		return "Record deleted";
	}
	
	// admin and user they both can access
	@GetMapping("/user/view")
	public String WelcomeUserMessage3()
	{
		return "You have logged in as user / So view rights are given";
	}
}