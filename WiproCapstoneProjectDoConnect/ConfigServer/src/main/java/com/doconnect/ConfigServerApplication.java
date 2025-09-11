package com.doconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication // Marks this as a Spring Boot application entry point
@EnableConfigServer // Enables Spring Cloud Config Server to serve configurations
public class ConfigServerApplication {

	public static void main(String[] args) {
		// Bootstraps and runs the Spring Boot Config Server application
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
