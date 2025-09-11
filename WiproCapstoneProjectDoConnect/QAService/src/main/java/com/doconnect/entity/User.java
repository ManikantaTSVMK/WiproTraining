package com.doconnect.entity;


 // Represents a User object from user-service.
 // This is NOT a JPA entity in qa-service.

public class User {

    private Long id; // Unique identifier for the user
    private String name; // Full name of the user
    private String username; // Username used for login/identification
    private String email; // User's email address
    private String phone; // User's phone number
    private boolean active; // Flag to indicate if the user account is active

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
