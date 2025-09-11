package com.doconnect.exception;

// Custom runtime exception for handling application-specific errors
public class CustomException extends RuntimeException {

    // Constructor that accepts a custom error message
    public CustomException(String message) {
        super(message);
    }
}
