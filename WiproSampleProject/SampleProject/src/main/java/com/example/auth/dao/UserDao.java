package com.example.auth.dao;

import com.example.auth.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    // ✅ Save new user
    public void save(User u) {
        if (u != null) {
            session().save(u);
        }
    }

    // ✅ Update existing user (merge is safer for detached entities)
    public void update(User u) {
        if (u != null && u.getId() != null) {
            session().merge(u);
        }
    }

    // ✅ Delete user by ID
    public void delete(Integer id) {
        User u = findById(id);
        if (u != null) {
            session().delete(u);
        }
    }

    // ✅ Find user by ID
    public User findById(Integer id) {
        if (id == null) return null;
        return session().get(User.class, id);
    }

    // ✅ Find user by username
    public User findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) return null;
        return session()
                .createQuery("FROM User WHERE username = :username", User.class)
                .setParameter("username", username)
                .uniqueResult();
    }

    // ✅ List all users
    public List<User> findAll() {
        return session()
                .createQuery("FROM User ORDER BY id ASC", User.class)
                .list();
    }

    // 🔒 Optional: Soft delete support (future enhancement)
    // public void deactivateUser(Integer id) {
    //     User u = findById(id);
    //     if (u != null) {
    //         u.setActive(false);
    //         update(u);
    //     }
    // }
}