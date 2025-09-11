package com.doconnect.entity;

import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity // Marks this class as a JPA entity mapped to a database table
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@AllArgsConstructor // Lombok: generates a constructor with all fields
@NoArgsConstructor  // Lombok: generates a no-args constructor
@Builder // Lombok: provides builder pattern for object creation
public class ChatMessage {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented ID
    private Long id; // Unique identifier for the chat message

    private String sender;   // Username of the sender
    private String receiver; // Username of the receiver
    private String message;  // Chat message content

    private LocalDateTime timestamp; // Time when the message was sent
}
