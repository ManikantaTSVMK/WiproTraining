package com.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-args constructor
public class NotificationRequest {
    private String to;       // Receiver's email address
    private String subject;  // Subject of the notification
    private String message;  // Body/content of the notification
}
