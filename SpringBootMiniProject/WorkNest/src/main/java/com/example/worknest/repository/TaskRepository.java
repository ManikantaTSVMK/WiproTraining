package com.example.worknest.repository;

import com.example.worknest.model.Task;
import com.example.worknest.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // 🔹 Find all tasks by status
    List<Task> findByStatus(TaskStatus status);

    // 🔹 Find all tasks by specific user (assignee)
    List<Task> findByAssignee_Id(Long userId);

    // 🔹 Find overdue tasks (due date before given date & not completed)
    List<Task> findByDueDateBeforeAndStatusNot(LocalDate date, TaskStatus status);

    // 🔹 Count tasks by status
    long countByStatus(TaskStatus status);

    // 🔹 Count overdue tasks (due date passed but not completed)
    long countByDueDateBeforeAndStatusNot(LocalDate date, TaskStatus status);

    // 🔹 Find tasks by group member (tasks assigned to groups where user is a member)
    List<Task> findByGroup_Members_Id(Long userId);

    // 🔹 Find all group tasks (tasks assigned to groups)
    List<Task> findByGroupIsNotNull();
}
