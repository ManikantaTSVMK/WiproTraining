package com.doconnect.controller;

import com.doconnect.dto.UserDTO;
import com.doconnect.dto.QuestionDTO;
import com.doconnect.dto.AnswerDTO;
import com.doconnect.entity.Admin;
import com.doconnect.feign.UserFeignClient;
import com.doconnect.feign.QAFeignClient;
import com.doconnect.feign.AnswerFeignClient;
import com.doconnect.service.AdminService;
import com.doconnect.security.JwtUtil;    

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/admin") // All endpoints in this controller are prefixed with /admin
public class AdminController {

    private final AdminService adminService;
    private final UserFeignClient userFeignClient;
    private final QAFeignClient qaFeignClient;
    private final AnswerFeignClient answerFeignClient;
    private final JwtUtil jwtUtil; 

    // Constructor injection for dependencies
    public AdminController(AdminService adminService,
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

    // ----------------- Admin Authentication -----------------

    // Show admin registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-register";
    }

    // Handle admin registration
    @PostMapping("/register")
    public String register(@ModelAttribute("admin") Admin admin, Model model) {
        try {
            adminService.register(admin); // Call service to register new admin
            model.addAttribute("success", "Admin registered successfully! Please login.");
            return "admin-login"; // Redirect to login page
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage()); // Show error if registration fails
            return "admin-register";
        }
    }

    // Show admin login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", null);
        return "admin-login";
    }

    // Handle admin login
    @PostMapping("/login")
    public String login(@RequestParam String emailOrPhone,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        try {
            // Authenticate admin
            Admin loggedInAdmin = adminService.login(emailOrPhone, password);

            // Generate JWT for session (currently just logged in console)
            String token = jwtUtil.generateToken(loggedInAdmin.getEmail(), "ADMIN");
            System.out.println("✅ Generated JWT Token for Admin: " + token);

            // Save admin in session
            session.setAttribute("admin", loggedInAdmin);
            model.addAttribute("admin", loggedInAdmin);

            return "admin-dashboard"; // Redirect to dashboard
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage()); // Show login error
            return "admin-login";
        }
    }

    // Logout admin and invalidate session
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        model.addAttribute("message", "You have been logged out.");
        return "admin-login";
    }

    // Show admin dashboard with statistics
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) { // Ensure admin is logged in
            model.addAttribute("error", "Please login again.");
            return "admin-login";
        }
        model.addAttribute("admin", admin);

        // Fetch user, question, and answer stats from microservices
        try {
            List<UserDTO> users = userFeignClient.getAllUsers();
            model.addAttribute("userCount", users.size());

            List<QuestionDTO> questions = qaFeignClient.getAllQuestions();
            model.addAttribute("questionCount", questions.size());
            long approvedQuestions = questions.stream().filter(QuestionDTO::isApproved).count();
            model.addAttribute("approvedQuestionCount", approvedQuestions);

            List<AnswerDTO> answers = answerFeignClient.getAllAnswers();
            model.addAttribute("answerCount", answers.size());
        } catch (Exception e) {
            // If services are down, show 0 counts
            model.addAttribute("userCount", 0);
            model.addAttribute("questionCount", 0);
            model.addAttribute("approvedQuestionCount", 0);
            model.addAttribute("answerCount", 0);
        }

        return "admin-dashboard";
    }

    // ----------------- User Management -----------------

    // List all users
    @GetMapping("/users")
    public String listUsers(Model model,
                            HttpSession session,
                            @RequestParam(required = false) String success,
                            @RequestParam(required = false) String error) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            model.addAttribute("error", "Please login again.");
            return "admin-login";
        }

        List<UserDTO> users = userFeignClient.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("admin", admin);

        if (success != null) model.addAttribute("success", success);
        if (error != null) model.addAttribute("error", error);

        return "admin-users";
    }

    // Update user details
    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String phone) {
        try {
            UserDTO user = new UserDTO();
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);

            userFeignClient.updateUser(id, user);
            return "redirect:/admin/users?success=User updated successfully!";
        } catch (Exception e) {
            String encodedError = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            return "redirect:/admin/users?error=" + encodedError;
        }
    }

    // Toggle user active/inactive status
    @GetMapping("/users/toggle/{id}")
    public String toggleUser(@PathVariable Long id) {
        try {
            userFeignClient.toggleUserStatus(id);
            return "redirect:/admin/users?success=User status updated!";
        } catch (Exception e) {
            return "redirect:/admin/users?error=" + e.getMessage();
        }
    }

    // Deactivate user
    @GetMapping("/users/deactivate/{id}")
    public String deactivateUser(@PathVariable Long id) {
        try {
            userFeignClient.deactivateUser(id);
            return "redirect:/admin/users?success=User deactivated successfully!";
        } catch (Exception e) {
            return "redirect:/admin/users?error=" + e.getMessage();
        }
    }

    // Add new user
    @PostMapping("/users/add")
    public String addUser(@RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String phone,
                          @RequestParam String password) {
        try {
            UserDTO user = new UserDTO();
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            user.setActive(true); // By default, new user is active

            userFeignClient.createUser(user);
            return "redirect:/admin/users?success=User added successfully!";
        } catch (Exception e) {
            String encodedError = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            return "redirect:/admin/users?error=" + encodedError;
        }
    }

    // ----------------- Question Management -----------------

    // List all questions
    @GetMapping("/questions")
    public String listQuestions(Model model,
                                HttpSession session,
                                @RequestParam(required = false) String success,
                                @RequestParam(required = false) String error) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            model.addAttribute("error", "Please login again.");
            return "admin-login";
        }

        List<QuestionDTO> questions = qaFeignClient.getAllQuestions();
        model.addAttribute("questions", questions);
        model.addAttribute("admin", admin);

        if (success != null) model.addAttribute("success", success);
        if (error != null) model.addAttribute("error", error);

        return "admin-questions";
    }

    // Approve a question
    @GetMapping("/questions/approve/{id}")
    public String approveQuestion(@PathVariable Long id) {
        try {
            qaFeignClient.approveQuestion(id);
            return "redirect:/admin/questions?success=Question approved!";
        } catch (Exception e) {
            return "redirect:/admin/questions?error=" + e.getMessage();
        }
    }

    // Reject a question
    @GetMapping("/questions/reject/{id}")
    public String rejectQuestion(@PathVariable Long id) {
        try {
            qaFeignClient.rejectQuestion(id);
            return "redirect:/admin/questions?success=Question rejected!";
        } catch (Exception e) {
            return "redirect:/admin/questions?error=" + e.getMessage();
        }
    }

    // Delete a question
    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        try {
            qaFeignClient.deleteQuestion(id);
            return "redirect:/admin/questions?success=Question deleted successfully!";
        } catch (Exception e) {
            return "redirect:/admin/questions?error=" + e.getMessage();
        }
    }

    // Mark question as resolved
    @GetMapping("/questions/resolve/{id}")
    public String resolveQuestion(@PathVariable Long id) {
        try {
            qaFeignClient.resolveQuestion(id);
            return "redirect:/admin/questions?success=Question marked as resolved!";
        } catch (Exception e) {
            return "redirect:/admin/questions?error=" + e.getMessage();
        }
    }

    // Add a new question
    @PostMapping("/questions/add")
    public String addQuestion(@RequestParam String title,
                              @RequestParam String description,
                              @RequestParam Long userId) {
        try {
            QuestionDTO question = new QuestionDTO();
            question.setTitle(title);
            question.setDescription(description);
            question.setUserId(userId);
            question.setApproved(false); // New questions need admin approval
            question.setResolved(false);

            qaFeignClient.createQuestion(question);
            return "redirect:/admin/questions?success=Question added successfully!";
        } catch (Exception e) {
            return "redirect:/admin/questions?error=" + e.getMessage();
        }
    }

    // ----------------- Answer Management -----------------

    // List all answers
    @GetMapping("/answers")
    public String listAnswers(Model model,
                              HttpSession session,
                              @RequestParam(required = false) String success,
                              @RequestParam(required = false) String error) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            model.addAttribute("error", "Please login again.");
            return "admin-login";
        }

        List<AnswerDTO> answers = answerFeignClient.getAllAnswers();
        model.addAttribute("answers", answers);
        model.addAttribute("admin", admin);

        if (success != null) model.addAttribute("success", success);
        if (error != null) model.addAttribute("error", error);

        return "admin-answers";
    }

    // Approve an answer
    @GetMapping("/answers/approve/{id}")
    public String approveAnswer(@PathVariable Long id) {
        try {
            answerFeignClient.approveAnswer(id);
            return "redirect:/admin/answers?success=Answer approved!";
        } catch (Exception e) {
            return "redirect:/admin/answers?error=" + e.getMessage();
        }
    }

    // Reject an answer
    @GetMapping("/answers/reject/{id}")
    public String rejectAnswer(@PathVariable Long id) {
        try {
            answerFeignClient.rejectAnswer(id);
            return "redirect:/admin/answers?success=Answer rejected!";
        } catch (Exception e) {
            return "redirect:/admin/answers?error=" + e.getMessage();
        }
    }

    // Delete an answer
    @GetMapping("/answers/delete/{id}")
    public String deleteAnswer(@PathVariable Long id) {
        try {
            answerFeignClient.deleteAnswer(id);
            return "redirect:/admin/answers?success=Answer deleted successfully!";
        } catch (Exception e) {
            return "redirect:/admin/answers?error=" + e.getMessage();
        }
    }
}
