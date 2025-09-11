package com.doconnect.service;

import com.doconnect.dto.NotificationRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service // Marks this as a Spring service component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender; // Spring's mail sender utility

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(NotificationRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage(); // Create a simple email message
            message.setTo(request.getTo()); // Set recipient
            message.setSubject(request.getSubject()); // Set subject
            message.setText(request.getMessage()); // Set body content

            mailSender.send(message); // Send email
            System.out.println(" Email sent to: " + request.getTo());
        } catch (Exception e) {
            System.err.println(" Failed to send email to " + request.getTo() + ": " + e.getMessage());
            throw e; // Re-throw exception for handling
        }
    }
}
