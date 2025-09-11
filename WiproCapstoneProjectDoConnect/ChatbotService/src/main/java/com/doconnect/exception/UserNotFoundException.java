package com.doconnect.exception;

// Custom exception for invalid or non-existent user in chat-service
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) { // Constructor to pass custom error message
        super(message);
    }
}
