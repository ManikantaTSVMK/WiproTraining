package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.AppConfig;
import com.example.entity.Course;
import com.example.entity.ReportCard;
import com.example.entity.Student;
import com.example.service.StudentService;


//App.java --> Controller  --> Service --- > Repository -- JPA --> Hibernate ---> jdbc --- >Database
public class App 
{
    public static void main( String[] args )
    {
    
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
         
    	    
         StudentService service =  ctx.getBean(StudentService.class);
         Student student = new Student("sri");
         
         student.addCourse(new Course("Javaa"));
         student.addCourse(new Course("jsp"));
         ReportCard rc = new ReportCard(490);
          student.setReportcard(rc);
          service.saveData(student);
    }
}