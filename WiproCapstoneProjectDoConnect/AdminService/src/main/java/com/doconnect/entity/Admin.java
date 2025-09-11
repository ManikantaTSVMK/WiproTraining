package com.doconnect.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity // Marks this class as a JPA entity
@Data  // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@Table(name = "admins") // Maps this entity to the "admins" table
public class Admin {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID generation
    private Long id; // Unique identifier for the admin

    @NotBlank(message = "Name is required") // Validation: name cannot be blank
    private String name; // Admin's name

    @Email(message = "Email should be valid and contain @") // Validation: must be a valid email
    @Column(unique = true, nullable = false) // Email must be unique and not null
    private String email; // Admin's email address

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits") // Validation: 10-digit phone number
    @Column(unique = true, nullable = false) // Phone must be unique and not null
    private String phone; // Admin's phone number

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
             message = "Password must be min 8 chars, with 1 letter, 1 number, 1 special char") // Validation for strong password
    private String password; // Admin's password

    private boolean active = true; // Flag to indicate if the admin is active

}
