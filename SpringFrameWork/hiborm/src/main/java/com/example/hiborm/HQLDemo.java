package com.example.hiborm;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.hiborm.model.Employee;
import com.example.hiborm.util.HibernateUtil;

public class HQLDemo {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;

        // INSERT
        tx = session.beginTransaction();
        Employee emp = new Employee("Phanim", "Phanii@example.com");
        session.persist(emp);
        tx.commit();
        System.out.println("Inserted: " + emp);

        // READ
        tx = session.beginTransaction();
        List<Employee> empList = session.createQuery("FROM Employee", Employee.class).getResultList();
        empList.forEach(System.out::println);
        tx.commit();

        // UPDATE
        tx = session.beginTransaction();
        session.createQuery("UPDATE Employee e SET e.email = :email WHERE e.id = :id")
                .setParameter("email", "Phanii.m@company.com")
                .setParameter("id", emp.getId())
                .executeUpdate();
        tx.commit();
        System.out.println("Updated employee with ID " + emp.getId());

        // DELETE
//        tx = session.beginTransaction();
//        session.createQuery("DELETE FROM Employee e WHERE e.id = :id")
//                .setParameter("id", emp.getId())
//                .executeUpdate();
//        tx.commit();
//        System.out.println("Deleted employee with ID " + emp.getId());

        session.close();
        HibernateUtil.close();
    }
}