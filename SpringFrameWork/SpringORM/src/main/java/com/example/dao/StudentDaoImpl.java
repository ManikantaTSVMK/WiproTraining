package com.example.dao;

import com.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository   // ✅ Marks this as a DAO bean
@Transactional // ✅ Enables transactional behavior
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Student student) {
        getSession().save(student);
    }

    @Override
    public Student findById(Long id) {
        return getSession().get(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return getSession().createQuery("from Student", Student.class).list();
    }

    @Override
    public void update(Student student) {
        getSession().update(student);
    }

    @Override
    public void delete(Long id) {
        Student student = findById(id);
        if (student != null) {
            getSession().delete(student);
        }
    }
}
