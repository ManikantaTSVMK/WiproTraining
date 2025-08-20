package com.gl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl.web.User;
import com.gl.web.UserDao;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserDao dao;

	@RequestMapping(method = RequestMethod.GET)
	public String getUser(Model model) {
		model.addAttribute("msg", "enter your details");
		return "Signin";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String loginUser(Model model, @ModelAttribute("user") User user) {
		if (user.getEmail() != null && user.getPassword() != null) {

			dao.saveUser(user);
			model.addAttribute("msg", user.getName());
			return "success";

		} else {
			model.addAttribute("error", "user details cant be empty");
			return "Signin";
		}
	}
	
	@RequestMapping(value="/getall",method = RequestMethod.GET)
	public String getAllUsers(Model model) {
	
	List<User> list=dao.getUsers();
	
	
		 model.addAttribute("userlist", list.toArray());
		return "homeuser";
	}
	
	
	
	
	
}
