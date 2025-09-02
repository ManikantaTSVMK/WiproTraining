package com.example.worknest.repository;

import com.example.worknest.model.Notification;
import com.example.worknest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientAndReadFalse(User recipient);
    List<Notification> findByRecipient(User recipient);
}
