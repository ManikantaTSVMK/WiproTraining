package com.doconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // Marks this as a Spring Boot application entry point
@EnableFeignClients(basePackages = "com.doconnect.feign") //  Enables Feign clients in the specified package
public class ChatbotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotServiceApplication.class, args); // Bootstraps the Spring Boot app
	}

}
