package com.example.auth.dao;

import com.example.auth.model.Task;
import com.example.auth.model.TaskStatus;
import com.example.auth.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    // ✅ Save new task
    public void save(Task t) {
        if (t != null) {
            session().save(t);
        }
    }

    // ✅ Update task (merge is safer for detached entities)
    public void update(Task t) {
        if (t != null && t.getId() != null) {
            session().merge(t);
        }
    }

    // ✅ Update only status by ID
    public void updateStatusById(Integer id, TaskStatus status) {
        if (id != null && status != null) {
            Task task = findById(id);
            if (task != null) {
                task.setStatus(status);
                session().merge(task);
            }
        }
    }

    // ✅ Delete task by ID
    public void delete(Integer id) {
        Task task = findById(id);
        if (task != null) {
            session().delete(task);
        }
    }

    // ✅ Find task by ID with assignee eagerly loaded
    public Task findById(Integer id) {
        if (id == null) return null;
        return session().createQuery(
                "SELECT t FROM Task t " +
                "LEFT JOIN FETCH t.assignee " +
                "WHERE t.id = :id",
                Task.class
        ).setParameter("id", id)
         .uniqueResult();
    }

    // ✅ List all tasks with assignees
    public List<Task> findAll() {
        return session().createQuery(
                "SELECT DISTINCT t FROM Task t " +
                "LEFT JOIN FETCH t.assignee " +
                "ORDER BY t.id ASC",
                Task.class
        ).list();
    }

    // ✅ List tasks by assignee
    public List<Task> findByAssignee(User u) {
        if (u == null) return List.of();
        return session().createQuery(
                "SELECT DISTINCT t FROM Task t " +
                "LEFT JOIN FETCH t.assignee " +
                "WHERE t.assignee = :u",
                Task.class
        ).setParameter("u", u)
         .list();
    }

    // ✅ Count tasks by status
    public long countByStatus(TaskStatus status) {
        if (status == null) return 0;
        return session().createQuery(
                "SELECT COUNT(t) FROM Task t WHERE t.status = :status",
                Long.class
        ).setParameter("status", status)
         .getSingleResult();
    }

    // ✅ Count all tasks
    public long countAll() {
        return session().createQuery(
                "SELECT COUNT(t) FROM Task t",
                Long.class
        ).getSingleResult();
    }

    // ✅ Count all statuses in one go (for dashboard overview)
    public Map<TaskStatus, Long> countAllStatuses() {
        Map<TaskStatus, Long> statusCounts = new EnumMap<>(TaskStatus.class);
        for (TaskStatus status : TaskStatus.values()) {
            long count = countByStatus(status);
            statusCounts.put(status, count);
        }
        return statusCounts;
    }
}