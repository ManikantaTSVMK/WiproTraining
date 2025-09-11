package com.doconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication // Marks this as a Spring Boot application entry point
@EnableEurekaServer // Enables Netflix Eureka Server for service registry
public class EurekaServerApplication {

	public static void main(String[] args) {
		// Bootstraps and runs the Eureka Server application
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
