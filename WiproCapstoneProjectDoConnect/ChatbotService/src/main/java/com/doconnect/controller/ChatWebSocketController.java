package com.doconnect.controller;

import com.doconnect.dto.ChatMessageDTO;
import com.doconnect.entity.ChatMessage;
import com.doconnect.exception.UserNotFoundException;
import com.doconnect.feign.UserFeignClient;
import com.doconnect.repository.ChatMessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;

@Controller // Marks this as a WebSocket controller
public class ChatWebSocketController {

    private final ChatMessageRepository chatMessageRepository; // Repository for storing chat messages
    private final UserFeignClient userFeignClient; // Feign client to validate users via user-service
    private final SimpMessagingTemplate messagingTemplate; // For sending messages over WebSocket

    public ChatWebSocketController(ChatMessageRepository chatMessageRepository,
                                   UserFeignClient userFeignClient,
                                   SimpMessagingTemplate messagingTemplate) {
        this.chatMessageRepository = chatMessageRepository;
        this.userFeignClient = userFeignClient;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage") // Endpoint for clients to send messages via STOMP
    public void sendMessage(ChatMessageDTO chatMessageDTO) {
        // Validate sender and receiver via user-service
        if (userFeignClient.getUserByUsername(chatMessageDTO.getSender()) == null) {
            throw new UserNotFoundException("Sender not found: " + chatMessageDTO.getSender());
        }
        if (userFeignClient.getUserByUsername(chatMessageDTO.getReceiver()) == null) {
            throw new UserNotFoundException("Receiver not found: " + chatMessageDTO.getReceiver());
        }

        // Set timestamp and save chat message
        chatMessageDTO.setTimestamp(LocalDateTime.now());
        ChatMessage chat = ChatMessage.builder()
                .sender(chatMessageDTO.getSender())
                .receiver(chatMessageDTO.getReceiver())
                .message(chatMessageDTO.getMessage())
                .timestamp(chatMessageDTO.getTimestamp())
                .build();

        chatMessageRepository.save(chat); // Persist chat message in DB

        // Send message to receiver’s private queue
        messagingTemplate.convertAndSendToUser(
                chatMessageDTO.getReceiver(),
                "/queue/messages",
                chatMessageDTO
        );

        // Echo message back to sender’s private queue
        messagingTemplate.convertAndSendToUser(
                chatMessageDTO.getSender(),
                "/queue/messages",
                chatMessageDTO
        );
    }
}
