package com.doconnect.service;

import com.doconnect.entity.User;
import java.util.List;

public interface UserService {
    User register(User user);
    User login(String emailOrPhone, String password);

    // CRUD methods for Admin
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deactivateUser(Long id);

    // Toggle active/inactive
    User toggleUserStatus(Long id);

    //  Methods for chat-service integration
    void logout(Long userId);                    // set status = OFFLINE
    String getStatusByUsername(String username); // return ONLINE / OFFLINE
    User getUserByUsername(String username);     // ✅ NEW

    //  New method: get all users currently ONLINE
    List<User> getOnlineUsers();
}
