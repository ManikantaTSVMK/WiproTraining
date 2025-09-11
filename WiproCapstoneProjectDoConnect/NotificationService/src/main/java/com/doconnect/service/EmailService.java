package com.doconnect.service;

import com.doconnect.dto.NotificationRequest;

public interface EmailService {

    // Send an email based on the notification request
    void sendEmail(NotificationRequest request);
}
