package com.doconnect.service;

import org.springframework.stereotype.Service;
import com.doconnect.entity.User;
import com.doconnect.exception.CustomException;
import com.doconnect.repository.UserRepository;

import java.util.List;

@Service // Marks this as a Spring service bean
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // Repository for accessing user data

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ================= Register & Login =================

    @Override
    public User register(User user) {
        // Check if user already exists by email or phone
        userRepository.findByEmailOrPhone(user.getEmail(), user.getPhone())
                .ifPresent(u -> {
                    throw new CustomException("User already registered with same email or phone");
                });
        user.setStatus("OFFLINE"); // New user is initially OFFLINE
        return userRepository.save(user);
    }

    @Override
    public User login(String emailOrPhone, String password) {
        // Authenticate user by email/phone and password
        return userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone)
                .map(u -> {
                    if (!u.isActive()) {
                        throw new CustomException("Your account is inactive. Please contact admin.");
                    }
                    if (!u.getPassword().equals(password)) {
                        throw new CustomException("Invalid password");
                    }
                    u.setStatus("ONLINE"); // Set status ONLINE on login
                    return userRepository.save(u);
                })
                .orElseThrow(() -> new CustomException("User not found"));
    }

    @Override
    public void logout(Long userId) {
        // Mark user as OFFLINE on logout
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found"));
        user.setStatus("OFFLINE");
        userRepository.save(user);
    }

    // ================= CRUD for Admin =================

    @Override
    public List<User> getAllUsers() { 
        return userRepository.findAll(); // Fetch all users
    }

    @Override
    public User getUserById(Long id) {
        // Fetch user by ID or throw exception
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        // Update user details (name, phone, etc.)
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));

        if (updatedUser.getName() != null && !updatedUser.getName().isBlank()) {
            existing.setName(updatedUser.getName());
        }
        if (updatedUser.getPhone() != null && !updatedUser.getPhone().isBlank()) {
            existing.setPhone(updatedUser.getPhone());
        }

        return userRepository.save(existing);
    }

    @Override
    public void deactivateUser(Long id) {
        // Deactivate user (set active = false)
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
        existing.setActive(false);
        userRepository.save(existing);
    }

    @Override
    public User toggleUserStatus(Long id) {
        // Toggle active/inactive status
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
        existing.setActive(!existing.isActive());
        return userRepository.save(existing);
    }

    // ================= Chat-related operations =================

    @Override
    public String getStatusByUsername(String username) {
        // Get user status (ONLINE/OFFLINE) by username
        return userRepository.findByName(username)
                .map(User::getStatus)
                .orElseThrow(() -> new CustomException("User not found with username: " + username));
    }

    @Override
    public User getUserByUsername(String username) {
        // Get user entity by username
        return userRepository.findByName(username)
                .orElseThrow(() -> new CustomException("User not found with username: " + username));
    }

    @Override
    public List<User> getOnlineUsers() {
        // Fetch all users currently ONLINE
        return userRepository.findByStatus("ONLINE");
    }
}
