package com.sample.controller;

import com.sample.model.User;
import com.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // ==== LOGIN ====

    // Common login page (for users)
    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login"; // JSP: /WEB-INF/views/auth/login.jsp
    }

    // Admin login page (separate JSP)
    @GetMapping("/login/admin")
    public String showAdminLoginPage() {
        return "auth/admin_login"; // JSP: /WEB-INF/views/auth/admin_login.jsp
    }

    // Process login (works for both Admin & User)
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        User user = userService.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/tasks";
            }
        }

        model.addAttribute("error", "Invalid credentials");
        return "auth/login"; // fallback to normal login page
    }

    // ==== REGISTER ====

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/register"; // JSP: /WEB-INF/views/auth/register.jsp
    }

    // Admin registration page (separate JSP)
    @GetMapping("/register/admin")
    public String showAdminRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/admin_register"; // JSP: /WEB-INF/views/auth/admin_register.jsp
    }
    

    // Process User registration
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user) {
        user.setRole("USER");
        userService.registerUser(user);
        return "redirect:/auth/login";
    }

    // Process Admin registration
    @PostMapping("/register/admin")
    public String processAdminRegister(@ModelAttribute("user") User user) {
        user.setRole("ADMIN");
        userService.registerUser(user);
        return "redirect:/auth/login/admin"; // after register, go to admin login
    }
    @PostMapping("/register/admin")
    public String processAdminRegister(@ModelAttribute("user") User user, Model model) {
        try {
            user.setRole("ADMIN");
            userService.registerUser(user);
            return "redirect:/auth/login/admin";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/admin_register"; // stay on register page
        }
    }

    // ==== LOGOUT ====
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/auth/login";
    }
}
