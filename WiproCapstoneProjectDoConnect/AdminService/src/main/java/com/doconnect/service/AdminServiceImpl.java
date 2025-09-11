package com.doconnect.service;

import org.springframework.stereotype.Service;

import com.doconnect.entity.Admin;
import com.doconnect.exception.CustomException;
import com.doconnect.repository.AdminRepository;

@Service // Marks this class as a Spring service component
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository; // Repository to handle Admin persistence

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin register(Admin admin) {
        // Check if admin already exists by email or phone
        adminRepository.findByEmailOrPhone(admin.getEmail(), admin.getPhone())
                .ifPresent(a -> { throw new CustomException("Admin already registered"); });
        return adminRepository.save(admin); // Save new admin
    }

    @Override
    public Admin login(String emailOrPhone, String password) {
        // Find admin by email/phone and validate password
        return adminRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone)
                .filter(a -> a.getPassword().equals(password)) // Simple password check (should use hashing in production)
                .orElseThrow(() -> new CustomException("Invalid credentials")); // Throw error if not found or invalid
    }
}
