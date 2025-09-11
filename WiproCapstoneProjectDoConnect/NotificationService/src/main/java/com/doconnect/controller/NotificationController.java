package com.doconnect.controller;

import com.doconnect.dto.NotificationRequest;
import com.doconnect.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Marks this as a REST controller
@RequestMapping("/api/notify") // Base URL for notification-related endpoints
public class NotificationController {

    private final EmailService emailService; // Service for sending emails

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    // Generic endpoint for sending notifications
    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        try {
            emailService.sendEmail(request); // Delegate to email service
            return ResponseEntity.ok("✅ Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError() // Handle errors gracefully
                    .body("❌ Failed to send email: " + e.getMessage());
        }
    }

    // Separate endpoint for question notifications
    @PostMapping("/question")
    public ResponseEntity<String> notifyQuestion(@RequestBody NotificationRequest request) {
        return sendNotification(request);
    }

    // Separate endpoint for answer notifications
    @PostMapping("/answer")
    public ResponseEntity<String> notifyAnswer(@RequestBody NotificationRequest request) {
        return sendNotification(request);
    }

    // Separate endpoint for approval notifications
    @PostMapping("/approval")
    public ResponseEntity<String> notifyApproval(@RequestBody NotificationRequest request) {
        return sendNotification(request);
    }
}
