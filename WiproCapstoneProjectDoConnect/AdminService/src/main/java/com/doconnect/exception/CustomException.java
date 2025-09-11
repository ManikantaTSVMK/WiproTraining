package com.doconnect.exception;

// Custom runtime exception to handle application-specific errors
public class CustomException extends RuntimeException {
    public CustomException(String message) { // Constructor to pass error message
        super(message);
    }
}
