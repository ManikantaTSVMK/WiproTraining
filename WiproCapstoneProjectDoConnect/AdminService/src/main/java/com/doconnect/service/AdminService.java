package com.doconnect.service;

import com.doconnect.entity.Admin;

public interface AdminService {

    Admin register(Admin admin); // Register a new admin

    Admin login(String emailOrPhone, String password); // Authenticate admin using email/phone and password
}
