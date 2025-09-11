package com.doconnect.dto;


import lombok.Data;

// DTO to receive user details from user-service
@Data
public class UserDTO {
    private Long id;          // unique user id
    private String name;      // user name
    private String email;     // user email
    private String phone;     // user phone number
}