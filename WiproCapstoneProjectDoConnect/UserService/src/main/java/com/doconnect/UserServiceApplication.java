package com.doconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // Marks this as a Spring Boot application entry point
@EnableFeignClients(basePackages = "com.doconnect.feign") // Enables Feign clients in the specified package
public class UserServiceApplication {

	public static void main(String[] args) {
		// Bootstraps and runs the User Service application
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
