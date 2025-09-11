package com.doconnect.feign;

import com.doconnect.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service", contextId = "userClient")
public interface UserFeignClient {

    //  Matches UserController → @GetMapping("/api/users")
    @GetMapping("/api/users")
    List<UserDTO> getAllUsers();

    //  Matches @PostMapping("/api/users")
    @PostMapping("/api/users")
    UserDTO createUser(@RequestBody UserDTO user);

    //  Matches @GetMapping("/api/users/{id}")
    @GetMapping("/api/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    //  Matches @PutMapping("/api/users/{id}")
    @PutMapping("/api/users/{id}")
    UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user);

    //  Matches @PutMapping("/api/users/{id}/toggle")
    @PutMapping("/api/users/{id}/toggle")
    UserDTO toggleUserStatus(@PathVariable("id") Long id);

    // Matches @DeleteMapping("/api/users/{id}")
    @DeleteMapping("/api/users/{id}")
    String deactivateUser(@PathVariable("id") Long id);
}
