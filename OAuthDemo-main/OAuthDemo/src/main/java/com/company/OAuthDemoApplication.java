package com.company;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OAuthDemoApplication {

	
	@GetMapping
	public String welcom() {
		return "welcom com to home page";
	}
	
	
	@GetMapping("/user")
	public Principal user(Principal principal) {
		System.out.println("username :" + principal.getName());
		return principal;
	}
	public static void main(String[] args) {
		SpringApplication.run(OAuthDemoApplication.class, args);
	}

}
