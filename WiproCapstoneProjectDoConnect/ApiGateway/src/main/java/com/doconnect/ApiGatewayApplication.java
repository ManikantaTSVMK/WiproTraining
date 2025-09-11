package com.doconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Marks this class as the main entry point for Spring Boot, enabling auto-configuration and component scanning
public class ApiGatewayApplication {

    // Main method: the starting point of the application
    public static void main(String[] args) {
        // Launches the Spring Boot application and initializes the API Gateway
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
