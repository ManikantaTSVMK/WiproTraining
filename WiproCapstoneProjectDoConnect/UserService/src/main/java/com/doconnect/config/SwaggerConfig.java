package com.doconnect.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Marks this as a configuration class
public class SwaggerConfig {

    @Bean // Defines a bean for OpenAPI configuration
    public OpenAPI customOpenAPI() {
        // Configure Swagger/OpenAPI documentation for User Service
        return new OpenAPI()
                .info(new Info()
                        .title("User Service API") // API title
                        .version("1.0") // API version
                        .description("APIs for User Service in DoConnect")); // API description
    }
}
