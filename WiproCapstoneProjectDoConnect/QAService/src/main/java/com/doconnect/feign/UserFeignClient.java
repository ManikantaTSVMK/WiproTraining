package com.doconnect.feign;

import com.doconnect.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", contextId = "userClientForQA") // Feign client for user-service (used in QA service)
public interface UserFeignClient {

    // Matches user-service → @GetMapping("/api/users/{id}")
    @GetMapping("/api/users/{id}") // Fetch user details by ID
    User getUserById(@PathVariable("id") Long id);
}
