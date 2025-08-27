package com.example.auth.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status = TaskStatus.PENDING; // default status

    // Eager fetch for JSP rendering
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id") // nullable → Unassigned allowed
    private User assignee;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskComment> comments = new HashSet<>();

    // --- Convenience Methods ---
    public boolean isDelayed() {
        return status != null && status == TaskStatus.DELAYED;
    }

    public boolean isCompleted() {
        return status != null && status == TaskStatus.COMPLETED;
    }

    public boolean isInProgress() {
        return status != null && status == TaskStatus.IN_PROGRESS;
    }

    public boolean isPending() {
        return status != null && status == TaskStatus.PENDING;
    }

    // ✅ Optional: Auto-mark as delayed if overdue
    public boolean isOverdue() {
        return dueDate != null && LocalDate.now().isAfter(dueDate) && !isCompleted();
    }

    // --- Getters and Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public User getAssignee() { return assignee; }
    public void setAssignee(User assignee) { this.assignee = assignee; }

    public Set<TaskComment> getComments() { return comments; }
    public void setComments(Set<TaskComment> comments) { this.comments = comments; }
}