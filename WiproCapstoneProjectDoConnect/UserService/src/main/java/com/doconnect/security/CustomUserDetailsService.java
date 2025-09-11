package com.doconnect.security;

import com.doconnect.entity.User;
import com.doconnect.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Marks this as a Spring-managed service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository; // Repository for accessing User data

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        // Look up user by email or phone; throw exception if not found
        User user = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + emailOrPhone));

        // Build Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()) // Use email as username
                .password(user.getPassword()) // Store encoded password
                .roles("USER") // Assign default role
                .build();
    }
}
