package com.doconnect.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.doconnect.dto.NotificationRequest;

//  Feign client to call notification-service
@FeignClient(name = "notification-service")
public interface NotificationFeignClient {

    @PostMapping("/api/notify/question") // Call notification-service to send new question notification
    void notifyNewQuestion(@RequestBody NotificationRequest request);

    @PostMapping("/api/notify/answer") // Call notification-service to send new answer notification
    void notifyNewAnswer(@RequestBody NotificationRequest request);

    @PostMapping("/api/notify") // Generic notification endpoint
    void sendNotification(@RequestBody NotificationRequest request);
}
