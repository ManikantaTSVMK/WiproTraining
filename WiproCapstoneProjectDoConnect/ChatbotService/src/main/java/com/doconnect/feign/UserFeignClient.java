package com.doconnect.feign;

import com.doconnect.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


 // Feign client used by chat-service to talk to user-service.
 // This assumes user-service exposes endpoints under "/api/users".
 
@FeignClient(name = "user-service", path = "/api/users")
public interface UserFeignClient {

    // Fetch user details by username.
     // Example: GET http://user-service/api/users/by-username/alice
     
    @GetMapping("/by-username/{username}")
    UserDTO getUserByUsername(@PathVariable("username") String username);

    
     //  Fetch user online/offline status.
     // Example: GET http://user-service/api/users/alice/status

    @GetMapping("/{username}/status")
    String getUserStatus(@PathVariable("username") String username);
}