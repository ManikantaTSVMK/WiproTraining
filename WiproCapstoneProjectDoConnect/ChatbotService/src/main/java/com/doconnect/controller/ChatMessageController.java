package com.doconnect.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.doconnect.dto.ChatMessageDTO;
import com.doconnect.entity.ChatMessage;
import com.doconnect.service.ChatMessageService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Marks this as a REST controller
@RequestMapping("/api/chat") // Base URL for chat-related APIs
@CrossOrigin(origins = "*") // Allow cross-origin requests from any source
public class ChatMessageController {

    private final ChatMessageService service; // Service layer for chat operations
    private final SimpMessagingTemplate messagingTemplate; // For sending WebSocket messages

    public ChatMessageController(ChatMessageService service, SimpMessagingTemplate messagingTemplate) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send") // API endpoint to send a chat message
    public ResponseEntity<?> sendMessage(@RequestParam String sender,
                                         @RequestParam String receiver,
                                         @RequestParam String message) {
        try {
            ChatMessage savedMessage = service.sendMessage(sender, receiver, message); // Save chat message

            ChatMessageDTO dto = new ChatMessageDTO(); // DTO for WebSocket response
            dto.setSender(sender);
            dto.setReceiver(receiver);
            dto.setMessage(message);
            dto.setTimestamp(LocalDateTime.now());

            // Push message to receiver's private queue
            messagingTemplate.convertAndSendToUser(receiver, "/queue/messages", dto);
            // Echo message back to sender
            messagingTemplate.convertAndSendToUser(sender, "/queue/messages", dto);

            Map<String, Object> resp = new HashMap<>();
            resp.put("chatMessage", savedMessage); // Saved message from DB
            resp.put("receiverStatus", service.getReceiverStatus(receiver)); // Receiver's online/offline status
            return ResponseEntity.ok(resp);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Handle errors gracefully
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/conversation") // API to get conversation history between two users
    public ResponseEntity<?> getConversation(@RequestParam String sender,
                                             @RequestParam String receiver) {
        List<ChatMessage> conversation = service.getConversation(sender, receiver);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/status/{username}") // API to get user status (online/offline)
    public ResponseEntity<?> getUserStatus(@PathVariable String username) {
        return ResponseEntity.ok(Map.of(
                "username", username,
                "status", service.getReceiverStatus(username)
        ));
    }
}
