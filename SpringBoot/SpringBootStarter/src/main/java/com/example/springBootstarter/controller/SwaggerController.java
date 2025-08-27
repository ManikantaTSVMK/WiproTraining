package com.example.springBootstarter.controller;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerController {
	
	@Bean
	public GroupedOpenApi api()
	{
		return GroupedOpenApi.builder()
				.group("private")
				.pathsToMatch("/customer/**")
				.build();
		
	}


}