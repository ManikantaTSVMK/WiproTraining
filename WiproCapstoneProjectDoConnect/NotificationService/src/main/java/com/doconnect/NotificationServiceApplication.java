package com.doconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Marks this as a Spring Boot application entry point
public class NotificationServiceApplication {

	public static void main(String[] args) {
		// Bootstraps and runs the Notification Service application
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
