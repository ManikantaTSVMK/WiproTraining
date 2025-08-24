package com.sample.controller;

import com.sample.model.Comment;
import com.sample.model.Task;
import com.sample.service.CommentService;
import com.sample.service.TaskService;
import com.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/tasks")
    public String viewTasks(@RequestParam("userId") Long userId, Model model) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        model.addAttribute("tasks", tasks);
        return "user/tasks";
    }

    @PostMapping("/tasks/updateStatus")
    public String updateTaskStatus(@RequestParam Long taskId,
                                   @RequestParam String status,
                                   @RequestParam Long userId) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            task.setStatus(status);
            taskService.updateTask(task);
        }
        return "redirect:/user/tasks?userId=" + userId;
    }

    @GetMapping("/comments/{taskId}")
    public String viewComments(@PathVariable("taskId") Long taskId, Model model) {
        List<Comment> comments = commentService.getCommentsByTaskId(taskId);
        model.addAttribute("comments", comments);
        model.addAttribute("taskId", taskId);
        model.addAttribute("comment", new Comment());
        return "user/comments";
    }

    @PostMapping("/comments/add")
    public String addComment(@ModelAttribute("comment") Comment comment,
                             @RequestParam Long taskId,
                             @RequestParam Long userId) {
        Task task = taskService.getTaskById(taskId);
        comment.setTask(task);
        comment.setUser(userService.getUserById(userId));
        commentService.addComment(comment);
        return "redirect:/user/comments/" + taskId;
    }
}
