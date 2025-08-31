package com.example.worknest.controller;

import com.example.worknest.model.Task;
import com.example.worknest.model.TaskStatus;
import com.example.worknest.model.User;
import com.example.worknest.service.TaskCommentService;
import com.example.worknest.service.TaskService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserTaskController {

    private final TaskService taskService;
    private final TaskCommentService commentService;

    /** ✅ Helper: get logged-in user */
    private User current(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    /** ✅ User dashboard: list my tasks with comments */
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        User me = current(session);
        if (me == null) return "redirect:/login";

        List<Task> myTasks = taskService.findByAssignee(me.getId());

        // ✅ Fetch comments separately (avoid modifying Task.comments directly)
        Map<Long, List<?>> taskComments = myTasks.stream()
                .collect(Collectors.toMap(
                        Task::getId,
                        t -> commentService.listByTask(t.getId())
                ));

        model.addAttribute("me", me);
        model.addAttribute("tasks", myTasks);
        model.addAttribute("taskComments", taskComments); 
        model.addAttribute("today", LocalDate.now());

        // ✅ Stats
        model.addAttribute("countPending", countByStatus(myTasks, TaskStatus.PENDING));
        model.addAttribute("countInProgress", countByStatus(myTasks, TaskStatus.IN_PROGRESS));
        model.addAttribute("countCompleted", countByStatus(myTasks, TaskStatus.COMPLETED));
        model.addAttribute("countDelayed", countDelayed(myTasks));

        return "user-dashboard";
    }

    /** ✅ View a task I am assigned to */
    @GetMapping("/tasks/{id}")
    public String viewTask(@PathVariable Long id, Model model, HttpSession session) {
        User me = current(session);
        if (me == null) return "redirect:/login";

        Task task = taskService.getById(id);
        if (!isAssignee(task, me)) return "redirect:/user/dashboard";

        model.addAttribute("task", task);
        model.addAttribute("comments", commentService.listByTask(id));
        model.addAttribute("today", LocalDate.now());
        return "user-task-details";
    }

    /** ✅ Update my task status */
    @PostMapping("/tasks/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam TaskStatus status,
                               HttpSession session) {
        User me = current(session);
        if (me == null) return "redirect:/login";

        Task task = taskService.getById(id);
        if (!isAssignee(task, me)) return "redirect:/user/dashboard";

        if (status == TaskStatus.PENDING || status == TaskStatus.IN_PROGRESS || status == TaskStatus.COMPLETED) {
            taskService.updateStatus(id, status);
        }

        return "redirect:/user/tasks/" + id;
    }

    /** ✅ Add a comment on my task */
    @PostMapping("/tasks/{id}/comments")
    public String addComment(@PathVariable Long id,
                             @RequestParam String content,
                             HttpSession session) {
        User me = current(session);
        if (me == null) return "redirect:/login";

        Task task = taskService.getById(id);
        if (!isAssignee(task, me)) return "redirect:/user/dashboard";

        commentService.add(id, me.getId(), content);
        return "redirect:/user/tasks/" + id;
    }

    // 🔧 Utility methods

    private boolean isAssignee(Task task, User user) {
        return task.getAssignee() != null && task.getAssignee().getId().equals(user.getId());
    }

    private long countByStatus(List<Task> tasks, TaskStatus status) {
        return tasks.stream().filter(t -> t.getStatus() == status).count();
    }

    private long countDelayed(List<Task> tasks) {
        LocalDate today = LocalDate.now();
        return tasks.stream().filter(t ->
                t.getStatus() != TaskStatus.COMPLETED &&
                t.getDueDate() != null &&
                t.getDueDate().isBefore(today)
        ).count();
    }
}
