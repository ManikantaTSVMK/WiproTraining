package com.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String to;       //  Receiver email
    private String subject;  //  Email subject
    private String message;  //  Email body
}
