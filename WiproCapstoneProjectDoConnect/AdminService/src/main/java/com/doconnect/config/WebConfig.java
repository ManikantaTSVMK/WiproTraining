package com.doconnect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Marks this class as a configuration class for Spring MVC
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Maps the URL "/h2-console/" to forward requests to "/h2-console"
        // This allows direct access to the H2 database console without creating a controller
        registry.addViewController("/h2-console/").setViewName("forward:/h2-console");
    }
}
