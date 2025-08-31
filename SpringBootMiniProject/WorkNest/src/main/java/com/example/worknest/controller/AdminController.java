package com.example.worknest.controller;

import com.example.worknest.model.*;
import com.example.worknest.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final TaskService taskService;
    private final TaskCommentService commentService;

    // DTO to show Task + Assignee in dashboard
    public static class TaskRow {
        private final Task task;
        private final User assignee;
        public TaskRow(Task task, User assignee) {
            this.task = task;
            this.assignee = assignee;
        }
        public Task getTask() { return task; }
        public User getAssignee() { return assignee; }
    }

    // ✅ Admin authentication/authorization check
    private String requireAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/user/dashboard";
        }
        return null; // Means access granted
    }

    // ===================== Dashboard =====================

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(defaultValue = "ALL") String filter,
                            Model model,
                            HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        // Add stats
        model.addAttribute("countPending", taskService.countByStatus(TaskStatus.PENDING));
        model.addAttribute("countInProgress", taskService.countByStatus(TaskStatus.IN_PROGRESS));
        model.addAttribute("countCompleted", taskService.countByStatus(TaskStatus.COMPLETED));
        model.addAttribute("countDelayed", taskService.countDelayed(LocalDate.now()));
        model.addAttribute("users", userService.findAll());

        // Filter tasks
        List<Task> tasks = switch (filter.toUpperCase()) {
            case "PENDING"     -> taskService.findByStatus(TaskStatus.PENDING);
            case "IN_PROGRESS" -> taskService.findByStatus(TaskStatus.IN_PROGRESS);
            case "COMPLETED"   -> taskService.findByStatus(TaskStatus.COMPLETED);
            case "DELAYED"     -> taskService.findDelayed(LocalDate.now());
            case "ALL"         -> taskService.findAll();
            default            -> taskService.findByStatus(TaskStatus.PENDING);
        };

        // Prepare rows
        List<TaskRow> rows = tasks.stream()
                .map(t -> new TaskRow(t, t.getAssignee()))
                .toList();
        model.addAttribute("rows", rows);

        // Load comments for each task
        Map<Long, List<TaskComment>> taskCommentsMap = new HashMap<>();
        for (Task t : tasks) {
            taskCommentsMap.put(t.getId(), commentService.listByTask(t.getId()));
        }
        model.addAttribute("taskCommentsMap", taskCommentsMap);

        model.addAttribute("filter", filter.toUpperCase());
        model.addAttribute("today", LocalDate.now());
        return "admin-dashboard";
    }

    // ===================== Page Views =====================

    @GetMapping("/add-task")
    public String showAddTaskPage(Model model, HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;
        model.addAttribute("users", userService.findAll());
        return "admin-add-task";
    }

    @GetMapping("/manage-users")
    public String showManageUsersPage(Model model, HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        model.addAttribute("users", userService.findAll());
        model.addAttribute("editUser", model.getAttribute("editUser"));
        return "admin-manage-users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable Long id, Model model, HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        model.addAttribute("editUser", userService.getById(id));
        model.addAttribute("users", userService.findAll());
        return "admin-manage-users";
    }

    // ===================== User CRUD =====================

    @PostMapping("/users")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String role,
                          HttpSession session,
                          Model model) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        try {
            userService.create(username, password, role);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("users", userService.findAll());
            return "admin-manage-users";
        }
        return "redirect:/admin/manage-users";
    }

    @PostMapping("/users/{id}/update")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String username,
                             @RequestParam String role,
                             @RequestParam(required = false) String password,
                             HttpSession session,
                             Model model) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        try {
            userService.update(id, username, role);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("editUser", userService.getById(id));
            model.addAttribute("users", userService.findAll());
            return "admin-manage-users";
        }
        return "redirect:/admin/manage-users";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;
        userService.delete(id);
        return "redirect:/admin/manage-users";
    }

    // ===================== Task CRUD =====================

    @PostMapping("/tasks")
    public String createTask(@RequestParam String title,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) List<Long> assigneeIds,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                             HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        try {
            taskService.createIndividualTasks(title, description, assigneeIds, startDate, dueDate);
        } catch (IllegalArgumentException e) {
            return "redirect:/admin/add-task?error=" + e.getMessage();
        }
        return "redirect:/admin/add-task?success=TaskCreated";
    }

    // User task status update (assignee only)
    @PostMapping("/tasks/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam TaskStatus status,
                               HttpSession session) {
        User me = (User) session.getAttribute("loggedInUser");
        Task task = taskService.getById(id);

        if (!task.getAssignee().getId().equals(me.getId())) {
            return "redirect:/user/dashboard?error=NotAllowed";
        }

        taskService.updateStatus(id, status);
        return "redirect:/user/tasks/" + id;
    }

    // ✅ Admin override of status update
    @PostMapping("/tasks/{id}/status/admin")
    public String adminUpdateStatus(@PathVariable Long id,
                                    @RequestParam TaskStatus status,
                                    HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        taskService.updateStatus(id, status);
        return "redirect:/admin/dashboard?success=StatusUpdated";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id, HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;
        taskService.delete(id);
        return "redirect:/admin/dashboard?success=TaskDeleted";
    }

    // ===================== Task Update =====================

    @GetMapping("/tasks/{id}/edit")
    public String showUpdateTaskForm(@PathVariable Long id, Model model, HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        model.addAttribute("task", taskService.getById(id));
        model.addAttribute("users", userService.findAll());
        return "admin-update-task";
    }

    @PostMapping("/tasks/{id}/update")
    public String updateTask(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam(required = false) String description,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                             @RequestParam TaskStatus status,
                             @RequestParam Long assigneeId,
                             HttpSession session,
                             Model model) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        try {
            taskService.updateTaskDetails(id, title, description, startDate, dueDate, status, assigneeId);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("task", taskService.getById(id));
            model.addAttribute("users", userService.findAll());
            return "admin-update-task";
        }
        return "redirect:/admin/dashboard?success=TaskUpdated";
    }

    // ===================== Task Details =====================

    @GetMapping("/tasks/{id}")
    public String taskDetails(@PathVariable Long id, Model model, HttpSession session) {
        String gate = requireAdmin(session);
        if (gate != null) return gate;

        Task task = taskService.getById(id);
        model.addAttribute("task", task);
        model.addAttribute("comments", commentService.listByTask(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("today", LocalDate.now());
        return "admin-task-details";
    }
}
