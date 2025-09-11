package com.example.worknest.service;

import com.example.worknest.model.Group;
import com.example.worknest.model.Task;
import com.example.worknest.model.TaskStatus;
import com.example.worknest.model.User;
import com.example.worknest.repository.GroupRepository;
import com.example.worknest.repository.TaskRepository;
import com.example.worknest.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;
    private final GroupRepository groupRepo;
    private final NotificationService notificationService;

    /**
     * Create individual tasks for multiple assignees
     */
    public void createIndividualTasks(String title, String description, List<Long> assigneeIds,
                                      LocalDate startDate, LocalDate dueDate) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (startDate == null || dueDate == null) {
            throw new IllegalArgumentException("Start and due dates are required");
        }
        if (dueDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Due date cannot be before start date");
        }
        if (assigneeIds == null || assigneeIds.isEmpty()) {
            throw new IllegalArgumentException("At least one assignee is required");
        }

        for (Long userId : assigneeIds) {
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Assignee not found with ID " + userId));

            Task task = Task.builder()
                    .title(title.trim())
                    .description(description)
                    .startDate(startDate)
                    .dueDate(dueDate)
                    .status(TaskStatus.PENDING)
                    .assignee(user)
                    .build();

            taskRepo.save(task);
        }
    }

    /**
     * Create a task assigned to a group
     */
    public Task createGroupTask(String title, String description, Long groupId,
                                LocalDate startDate, LocalDate dueDate) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (startDate == null || dueDate == null) {
            throw new IllegalArgumentException("Start and due dates are required");
        }
        if (dueDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Due date cannot be before start date");
        }
        if (groupId == null) {
            throw new IllegalArgumentException("Group is required");
        }

        Group group = groupRepo.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with ID " + groupId));

        Task task = Task.builder()
                .title(title.trim())
                .description(description)
                .startDate(startDate)
                .dueDate(dueDate)
                .status(TaskStatus.PENDING)
                .group(group)
                .build();

        return taskRepo.save(task);
    }

    /**
     * Reassign a group task to individual group members
     */
    public void reassignToGroupMembers(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID " + taskId));

        if (task.getGroup() == null) {
            throw new IllegalArgumentException("Task is not assigned to a group");
        }

        Group group = task.getGroup();
        for (User member : group.getMembers()) {
            Task memberTask = Task.builder()
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .startDate(task.getStartDate())
                    .dueDate(task.getDueDate())
                    .status(TaskStatus.PENDING)
                    .assignee(member)
                    .build();

            taskRepo.save(memberTask);

            // Send notification to each member
            String message = "You have been assigned a new task: " + task.getTitle();
            notificationService.createNotification(message, member);
        }

        // Delete the original group task
        taskRepo.delete(task);
    }

    /**
     * Reassign a group task to selected group members only
     */
    public void reassignToSelectedMembers(Long taskId, List<Long> selectedMemberIds) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID " + taskId));

        if (task.getGroup() == null) {
            throw new IllegalArgumentException("Task is not assigned to a group");
        }

        if (selectedMemberIds == null || selectedMemberIds.isEmpty()) {
            throw new IllegalArgumentException("At least one member must be selected");
        }

        Group group = task.getGroup();
        Set<Long> groupMemberIds = group.getMembers().stream()
                .map(User::getId)
                .collect(Collectors.toSet());

        // Validate that selected members are actually part of the group
        for (Long memberId : selectedMemberIds) {
            if (!groupMemberIds.contains(memberId)) {
                throw new IllegalArgumentException("Selected member with ID " + memberId + " is not part of the group");
            }
        }

        // Create individual tasks for selected members
        for (Long memberId : selectedMemberIds) {
            User member = userRepo.findById(memberId)
                    .orElseThrow(() -> new EntityNotFoundException("Member not found with ID " + memberId));

            Task memberTask = Task.builder()
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .startDate(task.getStartDate())
                    .dueDate(task.getDueDate())
                    .status(TaskStatus.PENDING)
                    .assignee(member)
                    .build();

            taskRepo.save(memberTask);

            // Send notification to each selected member
            String message = "You have been assigned a new task: " + task.getTitle();
            notificationService.createNotification(message, member);
        }

        // Delete the original group task
        taskRepo.delete(task);
    }

    /**
     * Update only the status of a task
     */
    public Task updateStatus(Long taskId, TaskStatus status) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID " + taskId));

        if (status != null) {
            task.setStatus(status);
        }
        return taskRepo.save(task);
    }

    /**
     * Full task update method (safe for orphan-removal relations)
     */
    public Task updateTaskDetails(Long taskId, String title, String description,
                                  LocalDate startDate, LocalDate dueDate,
                                  TaskStatus status, Long assigneeId) {

        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID " + taskId));

        if (task.isFrozen()) {
            throw new IllegalStateException("Task is frozen and cannot be modified");
        }

        if (title != null && !title.isBlank()) {
            task.setTitle(title.trim());
        }
        if (description != null) {
            task.setDescription(description);
        }
        if (startDate != null) {
            task.setStartDate(startDate);
        }
        if (dueDate != null) {
            LocalDate effectiveStart = startDate != null ? startDate : task.getStartDate();
            if (dueDate.isBefore(effectiveStart)) {
                throw new IllegalArgumentException("Due date cannot be before start date");
            }
            task.setDueDate(dueDate);
        }
        if (status != null) {
            task.setStatus(status);
        }
        if (assigneeId != null) {
            User user = userRepo.findById(assigneeId)
                    .orElseThrow(() -> new EntityNotFoundException("Assignee not found with ID " + assigneeId));
            task.setAssignee(user);
        }

        // ⚠️ DO NOT replace task.getComments() with a new list here
        // comments must be modified only via TaskCommentService

        return taskRepo.save(task);
    }

    /**
     * Soft delete a task
     */
    public void delete(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID " + taskId));
        task.setDeleted(true);
        taskRepo.save(task);
    }

    public Task getById(Long taskId) {
        return taskRepo.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID " + taskId));
    }

    public void toggleFreeze(Long taskId) {
        Task task = getById(taskId);
        task.setFrozen(!task.isFrozen());
        taskRepo.save(task);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findByAssignee(Long userId) {
        return taskRepo.findByAssignee_IdAndDeletedFalse(userId);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findByStatus(TaskStatus status) {
        return taskRepo.findByStatusAndDeletedFalse(status);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findDelayed(LocalDate refDate) {
        return taskRepo.findByDueDateBeforeAndStatusNotAndDeletedFalse(refDate, TaskStatus.COMPLETED);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findAll() {
        return taskRepo.findByDeletedFalse();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public long countByStatus(TaskStatus status) {
        return taskRepo.countByStatusAndDeletedFalse(status);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public long countDelayed(LocalDate refDate) {
        return taskRepo.countByDueDateBeforeAndStatusNotAndDeletedFalse(refDate, TaskStatus.COMPLETED);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findByGroupMember(Long userId) {
        return taskRepo.findByGroup_Members_IdAndDeletedFalse(userId);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findAllGroupTasksForAdmin() {
        return taskRepo.findByGroupIsNotNullAndDeletedFalse();
    }
}
