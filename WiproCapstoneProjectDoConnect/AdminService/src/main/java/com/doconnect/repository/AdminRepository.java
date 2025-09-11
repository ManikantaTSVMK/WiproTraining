package com.doconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doconnect.entity.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> { // Repository for Admin entity
    Optional<Admin> findByEmailOrPhone(String email, String phone); // Custom query to find admin by email or phone
}
