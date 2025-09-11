package com.doconnect.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this class as a Spring Security configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter; // Custom JWT authentication filter

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean // Bean for password hashing
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // Bean for authentication manager
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean // Main security filter chain
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection for development (enable in production)
            .csrf(csrf -> csrf.disable())

            // Allow frames from same origin (needed for H2 console)
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )

            .authorizeHttpRequests(auth -> auth
                // Permit H2 console access
                .requestMatchers("/h2-console/**").permitAll()

                // Permit actuator endpoints
                .requestMatchers("/actuator/**").permitAll()

                // Permit static resources
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                // Allow all other requests (development mode)
                .anyRequest().permitAll()
            );

        // 🔹 To enable JWT-based security later, add filter here:
        // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
