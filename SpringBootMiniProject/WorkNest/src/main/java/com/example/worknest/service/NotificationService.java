package com.example.worknest.service;

import com.example.worknest.model.Notification;
import com.example.worknest.model.User;
import com.example.worknest.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepo;

    public Notification createNotification(String message, User recipient) {
        Notification notification = Notification.builder()
                .message(message)
                .recipient(recipient)
                .createdAt(LocalDateTime.now())
                .read(false)
                .build();

        return notificationRepo.save(notification);
    }

    public List<Notification> getUnreadNotifications(User recipient) {
        return notificationRepo.findByRecipientAndReadFalse(recipient);
    }

    public List<Notification> getAllNotifications(User recipient) {
        return notificationRepo.findByRecipient(recipient);
    }

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepo.save(notification);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepo.deleteById(notificationId);
    }
}
