package com.doconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.doconnect.dto.AnswerDTO;
import com.doconnect.dto.QuestionDTO;
import com.doconnect.entity.User;
import com.doconnect.exception.CustomException;
import com.doconnect.security.JwtUtil;
import com.doconnect.service.UserService;
import com.doconnect.feign.QAFeignClientForUser;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // Marks this as a Spring MVC Controller
@RequestMapping("/user") // Base path for user-related routes
public class UserController {

    private final UserService userService; // Service for user operations
    private final QAFeignClientForUser qaFeignClientForUser; // Feign client to interact with QA Service
    private final JwtUtil jwtUtil; // Utility for JWT generation and validation

    public UserController(UserService userService,
                          QAFeignClientForUser qaFeignClientForUser,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.qaFeignClientForUser = qaFeignClientForUser;
        this.jwtUtil = jwtUtil;
    }

    // ================= UI ROUTES =================

    @GetMapping("/register") // Show user registration form
    public String showRegisterForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "register";
    }

    @PostMapping("/register") // Handle user registration
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Please fix the errors in the form.");
            return "register";
        }
        try {
            userService.register(user);
            model.addAttribute("success", "User registered successfully! Please login.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("user", user);
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login") // Show login form
    public String showLoginForm(Model model) {
        model.addAttribute("error", null);
        return "login";
    }

    @PostMapping("/login") // Handle login and redirect to dashboard
    public String login(@RequestParam String emailOrPhone,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        try {
            User loggedInUser = userService.login(emailOrPhone, password);
            String token = jwtUtil.generateToken(loggedInUser.getEmail(), "USER");

            session.setAttribute("user", loggedInUser);
            session.setAttribute("jwt", token);

            List<QuestionDTO> myQuestions = qaFeignClientForUser.getQuestionsByUser(loggedInUser.getId());
            List<QuestionDTO> approvedQuestions = qaFeignClientForUser.getAllApprovedQuestions();

            model.addAttribute("user", loggedInUser);
            model.addAttribute("myQuestions", myQuestions);
            model.addAttribute("approvedQuestions", approvedQuestions);

            // Add counts for dashboard stats
            model.addAttribute("myQuestionsCount", myQuestions.size());
            model.addAttribute("approvedMyQuestionsCount", (int) myQuestions.stream().filter(q -> q.isApproved()).count());
            model.addAttribute("totalApprovedQuestionsCount", approvedQuestions.size());

            return "dashboard";
        } catch (CustomException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @PostMapping("/questions") // Ask a new question
    public String askQuestion(@RequestParam String title,
                              @RequestParam String description,
                              HttpSession session,
                              Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            model.addAttribute("error", "Please login first.");
            return "login";
        }

        QuestionDTO question = new QuestionDTO();
        question.setTitle(title);
        question.setDescription(description);
        question.setUserId(loggedInUser.getId());
        question.setAskedBy(loggedInUser.getName());

        qaFeignClientForUser.askQuestion(question);

        return "redirect:/user/dashboard?success=Question submitted!";
    }

    @PostMapping("/answers/{questionId}") // Submit an answer to a question
    public String giveAnswer(@PathVariable Long questionId,
                             @RequestParam String content,
                             HttpSession session,
                             Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            model.addAttribute("error", "Please login first.");
            return "login";
        }

        AnswerDTO answer = new AnswerDTO();
        answer.setContent(content);
        answer.setUserId(loggedInUser.getId());
        answer.setAnsweredBy(loggedInUser.getName());

        qaFeignClientForUser.giveAnswer(questionId, answer);

        return "redirect:/user/dashboard?success=Answer submitted!";
    }

    @PostMapping("/answers/{id}/like") // Like an answer
    public String likeAnswer(@PathVariable Long id) {
        qaFeignClientForUser.likeAnswer(id);
        return "redirect:/user/dashboard?success=Liked answer!";
    }

    @PostMapping("/answers/{id}/comment") // Add a comment to an answer
    public String addComment(@PathVariable Long id,
                             @RequestParam String comment) {
        qaFeignClientForUser.addComment(id, comment);
        return "redirect:/user/dashboard?success=Comment added!";
    }

    @GetMapping("/dashboard") // Show user dashboard
    public String showDashboard(HttpSession session, Model model,
                                @RequestParam(required = false) String success,
                                @RequestParam(required = false) String error) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            model.addAttribute("error", "Please login again.");
            return "login";
        }

        List<QuestionDTO> myQuestions = qaFeignClientForUser.getQuestionsByUser(loggedInUser.getId());
        List<QuestionDTO> approvedQuestions = qaFeignClientForUser.getAllApprovedQuestions();

        model.addAttribute("user", loggedInUser);
        model.addAttribute("myQuestions", myQuestions);
        model.addAttribute("approvedQuestions", approvedQuestions);

        // Add counts for dashboard stats
        model.addAttribute("myQuestionsCount", myQuestions.size());
        model.addAttribute("approvedMyQuestionsCount", (int) myQuestions.stream().filter(q -> q.isApproved()).count());
        model.addAttribute("totalApprovedQuestionsCount", approvedQuestions.size());

        if (success != null) model.addAttribute("success", success);
        if (error != null) model.addAttribute("error", error);

        return "dashboard";
    }

    @GetMapping("/logout") // Handle user logout
    public String logout(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser != null) {
            userService.logout(loggedInUser.getId());
        }
        session.invalidate();
        model.addAttribute("message", "You have been logged out.");
        return "login";
    }

    @GetMapping("/search") // Search questions by keyword
    public String searchQuestions(@RequestParam String keyword, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            model.addAttribute("error", "Please login again.");
            return "login";
        }

        List<QuestionDTO> results = qaFeignClientForUser.searchQuestions(keyword);

        model.addAttribute("user", loggedInUser);
        List<QuestionDTO> myQuestions = qaFeignClientForUser.getQuestionsByUser(loggedInUser.getId());
        List<QuestionDTO> approvedQuestions = qaFeignClientForUser.getAllApprovedQuestions();
        model.addAttribute("myQuestions", myQuestions);
        model.addAttribute("approvedQuestions", approvedQuestions);
        model.addAttribute("searchResults", results);

        // Add counts for dashboard stats
        model.addAttribute("myQuestionsCount", myQuestions.size());
        model.addAttribute("approvedMyQuestionsCount", (int) myQuestions.stream().filter(q -> q.isApproved()).count());
        model.addAttribute("totalApprovedQuestionsCount", approvedQuestions.size());

        return "dashboard";
    }

    @GetMapping("/ask-question") // Show form to ask a new question
    public String showAskQuestion(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            model.addAttribute("error", "Please login again.");
            return "login";
        }

        model.addAttribute("user", loggedInUser);
        return "ask-question";
    }

    @GetMapping("/my-questions") // Show questions asked by the logged-in user
    public String showMyQuestions(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            model.addAttribute("error", "Please login again.");
            return "login";
        }

        List<QuestionDTO> myQuestions = qaFeignClientForUser.getQuestionsByUser(loggedInUser.getId());

        model.addAttribute("user", loggedInUser);
        model.addAttribute("myQuestions", myQuestions);

        return "my-questions";
    }

    // ================= REST APIs =================

    @GetMapping("/api/users") // API to fetch all users
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/{id}") // API to fetch user by ID
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/api/users/by-username/{username}") // API to fetch user by username
    @ResponseBody
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/api/users/{id}") // API to update user details
    @ResponseBody
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PutMapping("/api/users/{id}/toggle") // API to toggle user active/inactive status
    @ResponseBody
    public User toggleUserStatus(@PathVariable Long id) {
        return userService.toggleUserStatus(id);
    }

    @DeleteMapping("/api/users/{id}") // API to deactivate user
    @ResponseBody
    public String deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return "User deactivated successfully";
    }

    @PostMapping("/api/logout") // API to logout user
    @ResponseBody
    public String apiLogout(@RequestParam Long userId) {
        userService.logout(userId);
        return "User logged out successfully";
    }

    @GetMapping("/api/users/{username}/status") // API to fetch user status by username
    @ResponseBody
    public String getUserStatus(@PathVariable String username) {
        return userService.getStatusByUsername(username);
    }

    @GetMapping("/api/users/online") // API to fetch all online users
    @ResponseBody
    public List<User> getOnlineUsers() {
        return userService.getOnlineUsers();
    }

    @PostMapping("/api/login") // API login for external clients
    @ResponseBody
    public ResponseEntity<?> apiLogin(@RequestParam String emailOrPhone,
                                      @RequestParam String password) {
        try {
            User loggedInUser = userService.login(emailOrPhone, password);
            String token = jwtUtil.generateToken(loggedInUser.getEmail(), "USER");

            Map<String, String> response = new HashMap<>();
            response.put("token", "Bearer " + token);
            response.put("userId", loggedInUser.getId().toString());
            response.put("role", "USER");
            response.put("status", loggedInUser.getStatus());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials: " + e.getMessage());
        }
    }
}
