package com.doconnect.service;

import com.doconnect.dto.UserDTO;
import com.doconnect.entity.ChatMessage;
import com.doconnect.exception.UserNotFoundException;
import com.doconnect.feign.UserFeignClient;
import com.doconnect.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service // Marks this class as a Spring service component
public class ChatMessageService {

    private final ChatMessageRepository repository; // Repository for persisting chat messages
    private final UserFeignClient userFeignClient; // Feign client to validate users and get status

    public ChatMessageService(ChatMessageRepository repository, UserFeignClient userFeignClient) {
        this.repository = repository;
        this.userFeignClient = userFeignClient;
    }

    // Send a chat message after validating sender & receiver
    public ChatMessage sendMessage(String sender, String receiver, String message) {
        // Validate sender
        UserDTO senderUser = userFeignClient.getUserByUsername(sender);
        if (senderUser == null) {
            throw new UserNotFoundException("Sender not found: " + sender);
        }

        // Validate receiver
        UserDTO receiverUser = userFeignClient.getUserByUsername(receiver);
        if (receiverUser == null) {
            throw new UserNotFoundException("Receiver not found: " + receiver);
        }

        // Check receiver status (ONLINE/OFFLINE)
        String receiverStatus = userFeignClient.getUserStatus(receiver);
        if ("OFFLINE".equalsIgnoreCase(receiverStatus)) {
            System.out.println("⚠️ Receiver " + receiver + " is OFFLINE. Message will be stored.");
        } else {
            System.out.println("✅ Receiver " + receiver + " is ONLINE. Message delivered.");
        }

        // Save chat message with timestamp
        ChatMessage chat = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

        return repository.save(chat); // Persist chat message
    }

    // Get conversation history between two users
    public List<ChatMessage> getConversation(String sender, String receiver) {
        return repository.findConversation(sender, receiver);
    }

    // Get online/offline status of a user
    public String getReceiverStatus(String receiver) {
        return userFeignClient.getUserStatus(receiver);
    }
}
