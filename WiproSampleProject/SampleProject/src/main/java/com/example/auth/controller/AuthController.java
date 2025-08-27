package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 📝 Show registration form
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // ✅ Handle registration
    @PostMapping("/register")
    public String doRegister(@ModelAttribute("user") User user, Model model) {
        if (authService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        authService.registerUser(user);
        model.addAttribute("success", "Registration successful. Please login.");
        return "login";
    }

    // 🔐 Show login form
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // 🔓 Handle login
    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpSession session,
                          Model model) {

        User user = authService.login(username, password);

        if (user == null) {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }

        // 🧠 Store user session
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        // 🚀 Redirect based on role
        return "ADMIN".equalsIgnoreCase(user.getRole())
                ? "redirect:/admin/dashboard"
                : "redirect:/user/tasks";
    }

    // 🚪 Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login"; // Updated path if using @RequestMapping("/auth")
    }
}