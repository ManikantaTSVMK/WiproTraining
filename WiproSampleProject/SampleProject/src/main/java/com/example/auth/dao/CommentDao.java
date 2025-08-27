package com.example.auth.dao;

import com.example.auth.model.Task;
import com.example.auth.model.TaskComment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void add(TaskComment c) {
        session().save(c);
    }

    // ✅ Fetch join to load author and task eagerly
    public List<TaskComment> findByTask(Task task) {
        return session().createQuery(
                "SELECT DISTINCT c FROM TaskComment c " +
                "LEFT JOIN FETCH c.author " +
                "LEFT JOIN FETCH c.task " +
                "WHERE c.task = :task " +
                "ORDER BY c.createdAt ASC",
                TaskComment.class
        ).setParameter("task", task)
         .list();
    }
}
