package com.example.validation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.validation.model.User;
import com.example.validation.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	
	//Autowired the Service
	@Autowired
	public UserService userService;
	

	
	@PostMapping("/add")
	public ResponseEntity saveUser(@RequestBody User u)
	{
		ResponseEntity responseEntity = null;
		
		User uobj = userService.saveUSer(u);
		if(uobj!=null)
			responseEntity = new ResponseEntity<>("Data  added", HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("Data not added", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return responseEntity;
			
	}
	
//	@PostMapping("/add")
//	public String saveUser(@Valid @RequestBody User u)
//	{
//		
//		
//		User uobj = userService.saveUSer(u);
//		if(uobj!=null)
//			return "Added";
//		else
//			return "Record not added";
//		
//		
//		
//	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> userList = userService.getAllUsers();
		return new ResponseEntity<>(userList , HttpStatus.OK);
	}

}