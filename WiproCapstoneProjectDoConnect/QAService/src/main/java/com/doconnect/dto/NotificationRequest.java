package com.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String to;       // email of recipient
    private String subject;  // subject of email
    private String message;  // body of email
}
