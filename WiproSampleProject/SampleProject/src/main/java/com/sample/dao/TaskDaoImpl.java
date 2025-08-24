package com.sample.dao;

import com.sample.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveTask(Task task) {
        getSession().save(task);
    }

    @Override
    public void updateTask(Task task) {
        getSession().update(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        if (task != null) {
            getSession().delete(task);
        }
    }

    @Override
    public Task getTaskById(Long id) {
        return getSession().get(Task.class, id);
    }

    @Override
    public List<Task> getAllTasks() {
        return getSession()
                .createQuery("FROM Task", Task.class)
                .list();
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return getSession()
                .createQuery("FROM Task t WHERE t.assignedTo.id = :userId", Task.class)
                .setParameter("userId", userId)
                .list();
    }
}
