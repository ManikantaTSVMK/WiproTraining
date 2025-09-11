package com.example.worknest.controller;

import com.example.worknest.model.Group;
import com.example.worknest.model.Task;
import com.example.worknest.model.TaskStatus;
import com.example.worknest.model.User;
import com.example.worknest.service.GroupService;
import com.example.worknest.service.NotificationService;
import com.example.worknest.service.TaskCommentService;
import com.example.worknest.service.TaskService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserTaskController {

    private final TaskService taskService;
    private final TaskCommentService commentService;
    private final GroupService groupService;
    private final NotificationService notificationService;

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
        List<Task> groupTasks = taskService.findByGroupMember(me.getId());

        // Combine lists without duplicates using LinkedHashSet to preserve order
        Set<Task> combinedTasks = new LinkedHashSet<>(myTasks);
        combinedTasks.addAll(groupTasks);
        List<Task> allTasks = new java.util.ArrayList<>(combinedTasks);

        // ✅ Fetch comments separately (avoid modifying Task.comments directly)
        Map<Long, List<?>> taskComments = allTasks.stream()
                .collect(Collectors.toMap(
                        Task::getId,
                        t -> commentService.listByTask(t.getId())
                ));

        model.addAttribute("me", me);
        model.addAttribute("tasks", allTasks);
        model.addAttribute("taskComments", taskComments);
        model.addAttribute("today", LocalDate.now());

        // ✅ Stats
        model.addAttribute("countPending", countByStatus(allTasks, TaskStatus.PENDING));
        model.addAttribute("countInProgress", countByStatus(allTasks, TaskStatus.IN_PROGRESS));
        model.addAttribute("countCompleted", countByStatus(allTasks, TaskStatus.COMPLETED));
        model.addAttribute("countDelayed", countDelayed(allTasks));

        // ✅ Notifications
        model.addAttribute("notifications", notificationService.getUnreadNotifications(me));

        return "user-dashboard";
    }

    /** ✅ View a task I am assigned to */
    @GetMapping("/tasks/{id}")
    public String viewTask(@PathVariable Long id, Model model, HttpSession session) {
        User me = current(session);
        if (me == null) return "redirect:/login";

        Task task = taskService.getById(id);
        if (!isAssignee(task, me) && !isGroupMember(task, me)) return "redirect:/user/dashboard";

        model.addAttribute("task", task);
        model.addAttribute("comments", commentService.listByTask(id));
        model.addAttribute("today", LocalDate.now());

        // Add group members for reassignment if task is part of a group
        if (task.getGroup() != null) {
            List<User> groupMembers = task.getGroup().getMembers().stream()
                    .filter(member -> !member.getId().equals(me.getId())) // Exclude current user
                    .collect(Collectors.toList());
            model.addAttribute("groupMembers", groupMembers);
        }

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
        if (!isAssignee(task, me) && !isGroupMember(task, me)) return "redirect:/user/dashboard";

        if (task.isFrozen()) {
            return "redirect:/user/tasks/" + id + "?error=TaskFrozen";
        }

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
        if (!isAssignee(task, me) && !isGroupMember(task, me)) return "redirect:/user/dashboard";

        if (task.isFrozen()) {
            return "redirect:/user/tasks/" + id + "?error=TaskFrozen";
        }

        commentService.add(id, me.getId(), content);
        return "redirect:/user/tasks/" + id;
    }

    /** ✅ Reassign task to another group member */
    @PostMapping("/tasks/{id}/reassign")
    public String reassignTask(@PathVariable Long id,
                               @RequestParam Long newAssigneeId,
                               HttpSession session) {
        User me = current(session);
        if (me == null) return "redirect:/login";

        Task task = taskService.getById(id);
        if (!isAssignee(task, me) && !isGroupMember(task, me)) return "redirect:/user/dashboard";

        if (task.isFrozen()) {
            return "redirect:/user/tasks/" + id + "?error=TaskFrozen";
        }

        // Check if task is part of a group and new assignee is a group member
        if (task.getGroup() == null) {
            return "redirect:/user/tasks/" + id + "?error=NotGroupTask";
        }

        Group group = task.getGroup();
        boolean isNewAssigneeInGroup = group.getMembers().stream()
                .anyMatch(member -> member.getId().equals(newAssigneeId));

        if (!isNewAssigneeInGroup) {
            return "redirect:/user/tasks/" + id + "?error=InvalidAssignee";
        }

        // Reassign the task
        taskService.updateTaskDetails(id, null, null, null, null, null, newAssigneeId);

        return "redirect:/user/tasks/" + id + "?success=Reassigned";
    }

    /** ✅ Mark notification as read */
    @PostMapping("/notifications/{id}/read")
    public String markNotificationAsRead(@PathVariable Long id, HttpSession session) {
        User me = current(session);
        if (me == null) return "redirect:/login";

        notificationService.markAsRead(id);
        return "redirect:/user/dashboard";
    }

    /** ✅ Dismiss notification */
    @PostMapping("/notifications/{id}/dismiss")
    public String dismissNotification(@PathVariable Long id, HttpSession session) {
        User me = current(session);
        if (me == null) return "redirect:/login";

        notificationService.deleteNotification(id);
        return "redirect:/user/dashboard";
    }

    // 🔧 Utility methods

    private boolean isAssignee(Task task, User user) {
        return task.getAssignee() != null && task.getAssignee().getId().equals(user.getId());
    }

    private boolean isGroupMember(Task task, User user) {
        return task.getGroup() != null && task.getGroup().getMembers().stream()
                .anyMatch(member -> member.getId().equals(user.getId()));
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
