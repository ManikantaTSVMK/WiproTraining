package com.doconnect.dto;

import lombok.Data;

@Data // Lombok annotation to auto-generate getters, setters, equals, hashCode, and toString
public class UserDTO {
    private Long id; // Unique identifier for the user
    private String name; // Full name of the user
    private String email; // Email address of the user
    private String phone; // Phone number of the user
    private String password; // Encrypted password of the user
    private boolean active; // Flag to indicate if the user account is active
}
