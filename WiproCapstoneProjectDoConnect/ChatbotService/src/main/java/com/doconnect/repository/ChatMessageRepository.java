package com.doconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doconnect.entity.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> { // Repository for ChatMessage entity

    // Fetch messages where (sender=A AND receiver=B) OR (sender=B AND receiver=A), ordered by timestamp ASC
    @Query("SELECT c FROM ChatMessage c " +
           "WHERE (c.sender = :user1 AND c.receiver = :user2) " +
           "   OR (c.sender = :user2 AND c.receiver = :user1) " +
           "ORDER BY c.timestamp ASC")
    List<ChatMessage> findConversation(@Param("user1") String user1,
                                       @Param("user2") String user2);
}
