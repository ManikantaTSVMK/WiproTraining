package com.sample.dao;

import com.sample.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveComment(Comment comment) {
        getSession().save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);
        if (comment != null) {
            getSession().delete(comment);
        }
    }

    @Override
    public Comment getCommentById(Long id) {
        return getSession().get(Comment.class, id);
    }

    @Override
    public List<Comment> getCommentsByTaskId(Long taskId) {
        return getSession()
                .createQuery("FROM Comment c WHERE c.task.id = :taskId", Comment.class)
                .setParameter("taskId", taskId)
                .list();
    }

    @Override
    public List<Comment> getCommentsByUserId(Long userId) {
        return getSession()
                .createQuery("FROM Comment c WHERE c.user.id = :userId", Comment.class)
                .setParameter("userId", userId)
                .list();
    }
}
