package com.wipro.main;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wipro.dao.DaoImpl;
import com.wipro.hibernate.HibernateConfig;
import com.wipro.model.Student;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class, DaoImpl.class);

        DaoImpl dao = context.getBean(DaoImpl.class);

        Student student = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Name :");
        student.setName(sc.next());
        System.out.println("Enter Student Email Id :");
        student.setEmail(sc.next());
        System.out.println("Enter the Course Name :");
        student.setCourse(sc.next());

        dao.saveStudent(student);
       // dao.getAllStudents();
       // dao.getStudentById(1);

        System.out.println("Student saved successfully!");

        context.close();
    }
}
