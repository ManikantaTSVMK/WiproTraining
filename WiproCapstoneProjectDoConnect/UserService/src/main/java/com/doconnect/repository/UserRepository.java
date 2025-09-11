package com.doconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doconnect.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Used for login/registration (checks both email or phone)
    Optional<User> findByEmailOrPhone(String email, String phone);

    // Fetch only active users
    List<User> findByActiveTrue();

    //  Fetch all users with given status (ONLINE / OFFLINE)
    List<User> findByStatus(String status);

    //  Fetch user by username (for chat-service integration)
    Optional<User> findByName(String name);

    //  Fetch user by email
    Optional<User> findByEmail(String email);

    //  Fetch user by phone
    Optional<User> findByPhone(String phone);
}
