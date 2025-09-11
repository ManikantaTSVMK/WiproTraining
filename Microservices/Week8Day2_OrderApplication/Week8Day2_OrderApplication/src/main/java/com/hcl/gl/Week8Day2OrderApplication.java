package com.hcl.gl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class Week8Day2OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Week8Day2OrderApplication.class, args);
	}
	
	@Bean  //object lifecycle will be activated till you run the program
	@LoadBalanced
	public RestTemplate restTemplate(){
	return new RestTemplate();
	}
}
