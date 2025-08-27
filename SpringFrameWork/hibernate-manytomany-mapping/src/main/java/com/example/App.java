package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.model.Course;
import com.example.model.Student;
import com.example.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// to obtain/get the session from sessionfactory (It can be multiple session based on multiple services or work)
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		// To create a  transaction ( Transient + Persistent) -- Transient when you do not to reflect and save in backend  but persistent to save into a backend
	   Transaction tx = session.beginTransaction();
	   Course c1 = new Course("jsp"); //7,8
	   Course c2 = new Course("servlet");
	   
	 
	   Student Mani =  new Student("Mani");
	   
	   
	   
	   
	    Mani.addCourse(c2);
	    
	   session.persist(Mani);
	   tx.commit();
	   
	  // Session session1 = HibernateUtil.getSessionFactory().openSession();
	  Course course  = session.get(Course.class,1L);
	  System.out.println("Course details :" + course.getTitle());
	  course.getStudents().forEach(st->System.out.println("These Students are enrolled :" + st.getName()));
	   
	   }
}