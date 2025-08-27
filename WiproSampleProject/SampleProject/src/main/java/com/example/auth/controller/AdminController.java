package com.example.auth.controller;

import com.example.auth.model.Task;
import com.example.auth.model.TaskStatus;
import com.example.auth.model.User;
import com.example.auth.service.AdminService;
import com.example.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AuthService authService;

    @Autowired
    public AdminController(AdminService adminService, AuthService authService) {
        this.adminService = adminService;
        this.authService = authService;
    }

    private boolean isAdmin(HttpSession s) {
        Object role = s.getAttribute("role");
        return role != null && "ADMIN".equals(role.toString());
    }

    // ✅ Dashboard: includes task status counts + task list
    @GetMapping("/dashboard")
    public String dashboard(HttpSession s, Model model,
                            @ModelAttribute("message") String message,
                            @ModelAttribute("error") String error) {
        if (!isAdmin(s)) return "redirect:/login";

        // ✅ Add task status counts to model
        Map<TaskStatus, Long> counts = adminService.getStatusCounts();
        model.addAttribute("counts", counts);

        // ✅ Add all tasks to model
        List<Task> tasks = adminService.listAllTasks();
        model.addAttribute("tasks", tasks);

        // ✅ Flash messages
        if (message != null && !message.isEmpty()) model.addAttribute("message", message);
        if (error != null && !error.isEmpty()) model.addAttribute("error", error);

        return "admin-dashboard";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable Integer id, HttpSession s, Model model) {
        if (!isAdmin(s)) return "redirect:/login";
        User user = adminService.getUserById(id);
        model.addAttribute("editUser", user);
        model.addAttribute("users", adminService.listUsers());
        model.addAttribute("newUser", new User());
        return "admin-users";
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute User user, HttpSession s, RedirectAttributes ra) {
        if (!isAdmin(s)) return "redirect:/login";
        User existing = adminService.getUserById(user.getId());
        if (existing != null) {
            user.setPassword(existing.getPassword());
            adminService.updateUser(user);
            ra.addFlashAttribute("message", "User updated successfully!");
        } else {
            ra.addFlashAttribute("error", "User not found!");
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String users(HttpSession s, Model model) {
        if (!isAdmin(s)) return "redirect:/login";
        model.addAttribute("users", adminService.listUsers());
        model.addAttribute("newUser", new User());
        return "admin-users";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("newUser") User user, HttpSession s, Model model) {
        if (!isAdmin(s)) return "redirect:/login";
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            model.addAttribute("error", "Password required");
            model.addAttribute("users", adminService.listUsers());
            return "admin-users";
        }
        if (authService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists");
            model.addAttribute("users", adminService.listUsers());
            return "admin-users";
        }
        authService.registerUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Integer id, HttpSession s, RedirectAttributes ra) {
        if (!isAdmin(s)) return "redirect:/login";
        adminService.deleteUser(id);
        ra.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/admin/users";
    }

    @GetMapping("/tasks/new")
    public String newTask(HttpSession s, Model model) {
        if (!isAdmin(s)) return "redirect:/login";
        model.addAttribute("users", adminService.listUsers());
        return "task-form";
    }

    @PostMapping("/tasks")
    public String createTask(@RequestParam String title,
                             @RequestParam(required = false) String description,
                             @RequestParam String startDate,
                             @RequestParam String dueDate,
                             @RequestParam Integer assigneeId,
                             HttpSession s,
                             RedirectAttributes ra) {
        if (!isAdmin(s)) return "redirect:/login";
        adminService.createTask(
                title,
                description,
                LocalDate.parse(startDate),
                LocalDate.parse(dueDate),
                assigneeId
        );
        ra.addFlashAttribute("message", "Task created successfully!");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/tasks/{taskId}/comments")
    public String viewComments(@PathVariable Integer taskId, HttpSession s, Model model) {
        if (!isAdmin(s)) return "redirect:/login";
        model.addAttribute("comments", adminService.getCommentsForTask(taskId));
        return "admin-task-comments";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable("id") Integer id, HttpSession s, RedirectAttributes ra) {
        if (!isAdmin(s)) return "redirect:/login";
        adminService.deleteTask(id);
        ra.addFlashAttribute("message", "Task deleted successfully!");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/tasks/{taskId}/status")
    public String updateTaskStatus(@PathVariable Integer taskId,
                                   @RequestParam("status") String status,
                                   HttpSession s,
                                   RedirectAttributes ra) {
        if (!isAdmin(s)) return "redirect:/login";
        try {
            adminService.updateTaskStatus(taskId, TaskStatus.valueOf(status));
            ra.addFlashAttribute("message", "Task status updated to " + status + "!");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", "Invalid task status selected!");
        }
        return "redirect:/admin/dashboard";
    }
}