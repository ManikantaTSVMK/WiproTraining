package com.doconnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice // Marks this class as a global exception handler for controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class) // Handles CustomException across the app
    public String handleCustomException(CustomException ex, Model model) {
        model.addAttribute("error", ex.getMessage()); // Pass error message to view
        return "login"; // Redirects to login page on error
    }

    @ExceptionHandler(ConstraintViolationException.class) // Handles validation-related exceptions
    public String handleValidationException(ConstraintViolationException ex, Model model) {
        model.addAttribute("error", ex.getMessage()); // Pass validation error to view
        return "register"; // Redirects to register page on error
    }
}
