package com.doconnect.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Marks this class as a configuration class for Spring
public class SwaggerConfig {

    @Bean // Defines a Spring Bean for OpenAPI configuration
    public OpenAPI customOpenAPI() {
        // Configures OpenAPI with API title, version, and description
        return new OpenAPI()
                .info(new Info()
                        .title("Admin Service API")
                        .version("1.0")
                        .description("APIs for Admin Service in DoConnect"));
    }
}
