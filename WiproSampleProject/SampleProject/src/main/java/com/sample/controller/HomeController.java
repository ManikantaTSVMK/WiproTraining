package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to WorkNest!");
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login"; // maps to /WEB-INF/views/login.jsp
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register"; // maps to /WEB-INF/views/signup.jsp
    }
}
