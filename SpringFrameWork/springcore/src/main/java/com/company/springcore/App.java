package com.company.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.company.springcore.annotationbasedconfig.AppConfig;
import com.company.springcore.annotationbeans.Customer;
import com.company.springcore.javabasedconfig.BeanConfig;
import com.company.springcore.javabasedconfig.Employee;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Example of XML Based Configuratation:" );
//        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
//        
//       Student s = context.getBean("stud1" , Student.class);
//       System.out.println(s.hashCode());
//       System.out.println( s.getSid());
//       System.out.println( s.getSname());
//       System.out.println( s.getSaddr());
//       
//       Student s1 = context.getBean("stud1" , Student.class);
//       System.out.println(s1.hashCode());
       
     
//    	System.out.println( "Example of Java Based Configuratation:" );
//    	
//    	ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
//    	Employee  emp = ctx.getBean("emp", Employee.class);
//    	emp.welcomeEmployee();
//    	System.out.println(emp.getAddr());
       
    	
    	System.out.println("Example of Annotation Based Config");
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    	Customer cust =	ctx.getBean(Customer.class);
    	cust.welcomeCustomer();
    		
    }
}


