package com.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {

    private String sender;    // Username/email of sender
    private String receiver;  // Username/email of receiver
    private String message;   // Message content
    private LocalDateTime timestamp; // When the message was sent
}