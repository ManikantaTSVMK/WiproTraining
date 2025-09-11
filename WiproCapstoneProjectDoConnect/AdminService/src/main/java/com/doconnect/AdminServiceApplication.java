package com.doconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // Enables Spring Boot auto-configuration and component scanning
@EnableFeignClients(basePackages = "com.doconnect.feign") // Enables Feign clients for inter-service communication
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args); // Bootstraps and runs the Spring Boot application
    }

}
