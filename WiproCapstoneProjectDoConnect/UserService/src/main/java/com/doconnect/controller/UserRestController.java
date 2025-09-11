package com.doconnect.controller;

import com.doconnect.dto.UserDTO;
import com.doconnect.entity.User;
import com.doconnect.exception.CustomException;
import com.doconnect.service.UserService;
import com.doconnect.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ✅ Dedicated REST controller for API endpoints under /api/* (separated from UI controller)
@RestController
@RequestMapping("/api") // Base path for API routes
public class UserRestController {

    private final UserService userService; // Service layer for user operations
    private final JwtUtil jwtUtil; // Utility for JWT generation and validation

    public UserRestController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/users") // Create/register a new user
    public User createUser(@RequestBody UserDTO userDTO) {
        // Validate password format
        if (userDTO.getPassword() == null || !userDTO.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
            throw new CustomException("Password must be at least 8 characters long and contain at least one letter, one number, and one special character.");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.isActive());

        return userService.register(user);
    }

    @GetMapping("/users") // Get all users
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}") // Get a user by ID
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/{username}/status") // Get user status by username
    public String getUserStatus(@PathVariable String username) {
        return userService.getStatusByUsername(username);
    }

    @GetMapping("/users/online") // Get list of all online users
    public List<User> getOnlineUsers() {
        return userService.getOnlineUsers();
    }

    @PutMapping("/users/{id}") // Update user details
    public User updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        // Validate password if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty() &&
            !userDTO.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
            throw new CustomException("Password must be at least 8 characters long and contain at least one letter, one number, and one special character.");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword());
        }

        return userService.updateUser(id, user);
    }

    @PutMapping("/users/{id}/toggle") // Toggle user active/inactive status
    public User toggleUser(@PathVariable Long id) {
        return userService.toggleUserStatus(id);
    }

    @DeleteMapping("/users/{id}") // Deactivate user account
    public String deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return "User deactivated successfully";
    }

    @PostMapping("/login") // API login endpoint (returns JWT + user details)
    public ResponseEntity<?> apiLogin(@RequestParam String emailOrPhone,
                                      @RequestParam String password) {
        try {
            User loggedInUser = userService.login(emailOrPhone, password);
            String token = jwtUtil.generateToken(loggedInUser.getEmail(), "USER");

            Map<String, String> response = new HashMap<>();
            response.put("token", "Bearer " + token);
            response.put("userId", loggedInUser.getId().toString());
            response.put("role", "USER");
            response.put("status", loggedInUser.getStatus());

            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body("Invalid credentials: " + e.getMessage());
        }
    }

    @PostMapping("/logout") // API logout endpoint (marks user offline)
    public ResponseEntity<?> apiLogout(@RequestParam Long userId) {
        try {
            userService.logout(userId);
            return ResponseEntity.ok("User logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Logout failed: " + e.getMessage());
        }
    }
}
