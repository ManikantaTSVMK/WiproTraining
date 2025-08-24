package com.sample.service;

import com.sample.model.User;
import java.util.List;

public interface UserService {
    void registerUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    User getUserByUsername(String username);
    User findByEmailAndPassword(String email, String password);
    List<User> getAllUsers();
}
