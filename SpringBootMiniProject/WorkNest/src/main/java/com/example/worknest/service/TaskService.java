package com.example.worknest.service;

import com.example.worknest.model.Task;
import com.example.worknest.model.TaskStatus;
import com.example.worknest.model.User;
import com.example.worknest.repository.TaskRepository;
import com.example.worknest.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

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
     * Delete a task safely
     */
    public void delete(Long taskId) {
        if (!taskRepo.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found with ID " + taskId);
        }
        taskRepo.deleteById(taskId);
    }

    public Task getById(Long taskId) {
        return taskRepo.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID " + taskId));
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findByAssignee(Long userId) {
        return taskRepo.findByAssignee_Id(userId);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findByStatus(TaskStatus status) {
        return taskRepo.findByStatus(status);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findDelayed(LocalDate refDate) {
        return taskRepo.findByDueDateBeforeAndStatusNot(refDate, TaskStatus.COMPLETED);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public long countByStatus(TaskStatus status) {
        return taskRepo.countByStatus(status);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public long countDelayed(LocalDate refDate) {
        return taskRepo.countByDueDateBeforeAndStatusNot(refDate, TaskStatus.COMPLETED);
    }
}
