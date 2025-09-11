package com.doconnect.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.doconnect.dto.NotificationRequest;

@FeignClient(name = "notification-service") // Feign client for communicating with Notification Service
public interface NotificationFeignClient {

    @PostMapping("/api/notify/question") // Send notification when a new question is created
    void notifyNewQuestion(@RequestBody NotificationRequest request);

    @PostMapping("/api/notify/answer") // Send notification when a new answer is created
    void notifyNewAnswer(@RequestBody NotificationRequest request);

    @PostMapping("/api/notify/approval") // Send notification when a question/answer is approved
    void notifyApproval(@RequestBody NotificationRequest request);
}
