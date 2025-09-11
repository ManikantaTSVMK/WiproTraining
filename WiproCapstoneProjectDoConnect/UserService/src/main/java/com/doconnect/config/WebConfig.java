package com.doconnect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this as a Spring configuration class
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward /h2-console/ requests to /h2-console for H2 database console access
        registry.addViewController("/h2-console/").setViewName("forward:/h2-console");
    }
}
