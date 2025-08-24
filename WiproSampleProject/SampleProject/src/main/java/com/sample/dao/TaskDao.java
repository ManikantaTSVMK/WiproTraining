package com.sample.dao;

import com.sample.model.Task;
import java.util.List;

public interface TaskDao {
    void saveTask(Task task);
    void updateTask(Task task);
    void deleteTask(Long id);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    List<Task> getTasksByUserId(Long userId);
}
