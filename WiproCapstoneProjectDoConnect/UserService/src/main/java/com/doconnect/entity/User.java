package com.doconnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity // Marks this class as a JPA entity
@Data // Lombok annotation to auto-generate getters, setters, equals, hashCode, and toString
@Table(name = "users") // Maps this entity to the "users" table in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key (ID)
    private Long id; // Unique identifier for the user

    @NotBlank(message = "Name is required") // Validation: name cannot be blank
    private String name; // Full name of the user

    @Email(message = "Email should be valid and contain @") // Validation: valid email format
    @Column(unique = true, nullable = false) // Must be unique and not null
    private String email; // Email address of the user

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits") // Validation: only 10-digit phone numbers
    @Column(unique = true, nullable = false) // Must be unique and not null
    private String phone; // Phone number of the user

    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
        message = "Password must be min 8 chars, with 1 letter, 1 number, 1 special char"
    ) // Validation: strong password format
    private String password; // Encrypted password of the user

    private boolean active = true; // Default active status (true when created)

    //  New field for tracking online/offline status
    private String status = "OFFLINE"; // Default OFFLINE
}
