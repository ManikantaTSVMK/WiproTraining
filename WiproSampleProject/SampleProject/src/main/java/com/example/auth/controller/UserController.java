package com.example.auth.controller;

import com.example.auth.model.Task;
import com.example.auth.model.TaskComment;
import com.example.auth.model.TaskStatus;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private Integer currentUserId(HttpSession s) {
        Object id = s.getAttribute("userId");
        return id == null ? null : (Integer) id;
    }

    @GetMapping("/tasks")
    public String myTasks(HttpSession s, Model model) {
        Integer uid = currentUserId(s);
        if (uid == null) return "redirect:/login";
        List<Task> tasks = userService.tasksForUser(uid);
        model.addAttribute("tasks", tasks);
        return "user-tasks";
    }

    @PostMapping("/tasks/{taskId}/status")
    public String updateStatus(@PathVariable Integer taskId,
                               @RequestParam("status") String status,
                               HttpSession s) {
        Integer uid = currentUserId(s);
        if (uid == null) return "redirect:/login";
        userService.updateStatus(taskId, TaskStatus.valueOf(status), uid);
        return "redirect:/user/tasks";
    }

    @GetMapping("/tasks/{taskId}")
    public String taskDetails(@PathVariable Integer taskId, HttpSession s, Model model) {
        Integer uid = currentUserId(s);
        if (uid == null) return "redirect:/login";
        List<TaskComment> comments = userService.commentsForTask(taskId);
        model.addAttribute("taskId", taskId);
        model.addAttribute("comments", comments);
        return "task-details";
    }

    @PostMapping("/tasks/{taskId}/comments")
    public String addComment(@PathVariable Integer taskId,
                             @RequestParam String content,
                             HttpSession s) {
        Integer uid = currentUserId(s);
        if (uid == null) return "redirect:/login";
        userService.addComment(taskId, uid, content);
        return "redirect:/user/tasks/" + taskId;
    }
}