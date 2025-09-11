package com.doconnect.controller;

import com.doconnect.dto.UserDTO;
import com.doconnect.dto.QuestionDTO;
import com.doconnect.dto.AnswerDTO;
import com.doconnect.entity.Admin;
import com.doconnect.service.AdminService;
import com.doconnect.feign.UserFeignClient;
import com.doconnect.feign.QAFeignClient;
import com.doconnect.feign.AnswerFeignClient;
import com.doconnect.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/admin") // Base URL mapping for all endpoints in this controller
@Tag(name = "Admin REST APIs", description = "APIs for Admin management in DoConnect") // Swagger documentation tag
public class AdminRestController {

    private final AdminService adminService; // Service to handle admin logic
    private final UserFeignClient userFeignClient; // Feign client for user service
    private final QAFeignClient qaFeignClient; // Feign client for question service
    private final AnswerFeignClient answerFeignClient; // Feign client for answer service
    private final JwtUtil jwtUtil; // Utility for generating JWT tokens

    public AdminRestController(AdminService adminService,
                               UserFeignClient userFeignClient,
                               QAFeignClient qaFeignClient,
                               AnswerFeignClient answerFeignClient,
                               JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.userFeignClient = userFeignClient;
        this.qaFeignClient = qaFeignClient;
        this.answerFeignClient = answerFeignClient;
        this.jwtUtil = jwtUtil;
    }

    // ==============================
    // 🔹 Authentication
    // ==============================
    @PostMapping("/register") // API endpoint to register new admin
    @Operation(summary = "Register new admin")
    public Admin register(@RequestBody Admin admin) {
        return adminService.register(admin);
    }

    @PostMapping("/login") // API endpoint for admin login and JWT generation
    @Operation(summary = "Login admin and generate JWT")
    public String login(@RequestParam String emailOrPhone, @RequestParam String password) {
        Admin loggedInAdmin = adminService.login(emailOrPhone, password);
        return jwtUtil.generateToken(loggedInAdmin.getEmail(), "ADMIN");
    }

    // ==============================
    // 🔹 User Management
    // ==============================
    @GetMapping("/users") // API to fetch all users
    @Operation(summary = "Get all users")
    public List<UserDTO> listUsers() {
        return userFeignClient.getAllUsers();
    }

    @PutMapping("/users/{id}") // API to update user details by ID
    @Operation(summary = "Update user details")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        return userFeignClient.updateUser(id, user);
    }

    @PatchMapping("/users/toggle/{id}") // API to toggle user status active/inactive
    @Operation(summary = "Toggle user active/inactive status")
    public String toggleUser(@PathVariable Long id) {
        userFeignClient.toggleUserStatus(id);
        return "User status updated!";
    }

    @PatchMapping("/users/deactivate/{id}") // API to deactivate a user
    @Operation(summary = "Deactivate user")
    public String deactivateUser(@PathVariable Long id) {
        userFeignClient.deactivateUser(id);
        return "User deactivated!";
    }

    // ==============================
    // 🔹 Question Management
    // ==============================
    @GetMapping("/questions") // API to fetch all questions
    @Operation(summary = "Get all questions")
    public List<QuestionDTO> listQuestions() {
        return qaFeignClient.getAllQuestions();
    }

    @PatchMapping("/questions/approve/{id}") // API to approve a question by ID
    @Operation(summary = "Approve question")
    public String approveQuestion(@PathVariable Long id) {
        qaFeignClient.approveQuestion(id);
        return "Question approved!";
    }

    @PatchMapping("/questions/reject/{id}") // API to reject a question by ID
    @Operation(summary = "Reject question")
    public String rejectQuestion(@PathVariable Long id) {
        qaFeignClient.rejectQuestion(id);
        return "Question rejected!";
    }

    @DeleteMapping("/questions/{id}") // API to delete a question by ID
    @Operation(summary = "Delete question")
    public String deleteQuestion(@PathVariable Long id) {
        qaFeignClient.deleteQuestion(id);
        return "Question deleted!";
    }

    @PatchMapping("/questions/resolve/{id}") // API to mark a question as resolved
    @Operation(summary = "Resolve question")
    public String resolveQuestion(@PathVariable Long id) {
        qaFeignClient.resolveQuestion(id);
        return "Question resolved!";
    }

    // ==============================
    // 🔹 Answer Management
    // ==============================
    @GetMapping("/answers") // API to fetch all answers
    @Operation(summary = "Get all answers")
    public List<AnswerDTO> listAnswers() {
        return answerFeignClient.getAllAnswers();
    }

    @PatchMapping("/answers/approve/{id}") // API to approve an answer by ID
    @Operation(summary = "Approve answer")
    public String approveAnswer(@PathVariable Long id) {
        answerFeignClient.approveAnswer(id);
        return "Answer approved!";
    }

    @PatchMapping("/answers/reject/{id}") // API to reject an answer by ID
    @Operation(summary = "Reject answer")
    public String rejectAnswer(@PathVariable Long id) {
        answerFeignClient.rejectAnswer(id);
        return "Answer rejected!";
    }

    @DeleteMapping("/answers/{id}") // API to delete an answer by ID
    @Operation(summary = "Delete answer")
    public String deleteAnswer(@PathVariable Long id) {
        answerFeignClient.deleteAnswer(id);
        return "Answer deleted!";
    }
}
