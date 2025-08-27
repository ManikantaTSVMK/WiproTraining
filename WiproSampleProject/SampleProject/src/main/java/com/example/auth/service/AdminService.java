package com.example.auth.service;

import com.example.auth.dao.CommentDao;
import com.example.auth.dao.TaskDao;
import com.example.auth.dao.UserDao;
import com.example.auth.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class AdminService {

    private final UserDao userDao;
    private final TaskDao taskDao;
    private final CommentDao commentDao;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public AdminService(UserDao userDao, TaskDao taskDao, CommentDao commentDao) {
        this.userDao = userDao;
        this.taskDao = taskDao;
        this.commentDao = commentDao;
    }

    // ✅ User Management
    public List<User> listUsers() {
        return userDao.findAll();
    }

    public void addUser(User user) {
        userDao.save(user);
    }

    public void deleteUser(Integer id) {
        userDao.delete(id);
    }

    public User getUserById(Integer id) {
        return userDao.findById(id);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    // ✅ Task Management
    public void createTask(String title, String description, LocalDate start, LocalDate due, Integer assigneeId) {
        User assignee = userDao.findById(assigneeId);
        Task t = new Task();
        t.setTitle(title);
        t.setDescription(description);
        t.setStartDate(start);
        t.setDueDate(due);
        t.setAssignee(assignee);
        t.setStatus(TaskStatus.PENDING); // default status
        taskDao.save(t);
    }

    public void updateTaskStatus(Integer taskId, TaskStatus status) {
        Task t = taskDao.findById(taskId);
        if (t != null) {
            t.setStatus(status);
            taskDao.update(t);
        }
    }

    public void deleteTask(Integer taskId) {
        Task task = taskDao.findById(taskId);
        if (task != null) {
            taskDao.delete(taskId);
        }
    }

    // ✅ Task Status Count Summary
    public Map<TaskStatus, Long> getStatusCounts() {
        Map<TaskStatus, Long> map = new EnumMap<>(TaskStatus.class);
        for (TaskStatus s : TaskStatus.values()) {
            long count = taskDao.countByStatus(s);
            map.put(s, count);
        }
        return map;
    }

    // ✅ Optional: Total Task Count
    public long getTotalTaskCount() {
        return taskDao.countAll();
    }

    // ✅ Load all tasks with assignee to avoid LazyInitializationException
    @Transactional(readOnly = true)
    public List<Task> listAllTasks() {
        return em.createQuery(
                "SELECT t FROM Task t LEFT JOIN FETCH t.assignee",
                Task.class
        ).getResultList();
    }

    // ✅ Load comments with author to avoid LazyInitializationException
    @Transactional(readOnly = true)
    public List<TaskComment> getCommentsForTask(Integer taskId) {
        return em.createQuery(
                "SELECT c FROM TaskComment c " +
                "LEFT JOIN FETCH c.author " +
                "WHERE c.task.id = :taskId " +
                "ORDER BY c.createdAt DESC",
                TaskComment.class
        )
        .setParameter("taskId", taskId)
        .getResultList();
    }
}