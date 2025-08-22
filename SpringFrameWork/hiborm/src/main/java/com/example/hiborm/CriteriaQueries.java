package com.example.hiborm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.sql.rowset.Predicate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.hiborm.model.Employee;
import com.example.hiborm.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CriteriaQueries {
	
	public static void main(String[] args) {
		  Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        
//	        // to insert 
//	        session.persist(new Employee("Jeevan","Jeevan@gmail.com"));
//	        tx.commit();
//	        System.out.println("Record inserted");
	        
	        // ----- Read the data using criteria builder
	       CriteriaBuilder cb =  session.getCriteriaBuilder(); //Obtain a HibernateCriteriaBuilder which may be used to construct criteria queries.
	        CriteriaQuery<Employee> cq =  cb.createQuery(Employee.class); // created a criteria query object
	       Root<Employee> r = cq.from(Employee.class); //A root type in the from clause. Query roots always reference entities.
	       cq.select(r);
	       
	       List<Employee> listOfEmp = session.createQuery(cq).getResultList();
	        
	       System.out.println("List Size :" + listOfEmp.size());
	       System.out.println("All Employees Data : " + listOfEmp);
	       
	       
	       // Stream works with collections --- Filter the records using name
	       System.out.println("********** filter by name ***********");
	       listOfEmp.stream().filter(e-> e.getName().equalsIgnoreCase("Niti"))
	       .forEach(System.out::println);
	
	    //  --- Filter the records by name using criteria API
	       System.out.println("********** filter by name using criteria api ***********");
	      
	      // CriteriaBuilder cb1 =  session.getCriteriaBuilder(); //Obtain a HibernateCriteriaBuilder which may be used to construct criteria queries.
	        CriteriaQuery<Employee> cqf =  cb.createQuery(Employee.class); // created a criteria query object
	       Root<Employee> rootfilter = cqf.from(Employee.class); //A root type in the from clause. Query roots always reference entities.
	       cqf.select(rootfilter)
	       	  .where(cb.equal(rootfilter.get("name"),"Niti"));
	       
	       Employee emp = session.createQuery(cqf).getSingleResult();
	       System.out.println("Data found :" + emp);
	       
	       
	      // System.out.println("********** filter by name using criteria api (Using static method)***********");
	      // searchEmployee(session ,"Niti" ,"%iti%").forEach(System.out::println);
	       
	       System.out.println("********** filter by Id ***********");
	       // Stream works with collections --- Filter the records using id
	      List<Employee> listofid =  listOfEmp.stream().filter(e-> e.getId() > 5 )
	       .toList();
	       listofid.forEach(System.out::println);
	       
	       System.out.println("********** Sorted List Ascending ***********");
	       listOfEmp.stream().sorted(Comparator.comparing(Employee::getName))
	       					.forEach(System.out::println);
	       
	       System.out.println("********** Sorted List Descending ***********");
	       listOfEmp.stream().sorted(Comparator.comparing(Employee::getName).reversed())
	       					.forEach(System.out::println);
	       
	       System.out.println("********** email in lowercase ***********");
	       listOfEmp.stream().sorted(Comparator.comparing(e1->((Employee) e1).getEmail().toLowerCase()))
	       					.forEach(System.out::println);
	       
	      
	}
	
	 public static List<Employee> searchEmployee(Session session, String name, String emailLike )
     {
  	    
  	   CriteriaBuilder cb =  session.getCriteriaBuilder(); //Obtain a HibernateCriteriaBuilder which may be used to construct criteria queries.
	        CriteriaQuery<Employee> cq =  cb.createQuery(Employee.class);
	        Root<Employee> rooot = cq.from(Employee.class);
	        
	        List<Predicate> predicates = new ArrayList<>();
	        
	        
	        if(name!=null)
	        {
	        	predicates.addAll((Collection<? extends Predicate>) cb.equal(rooot.get("name"),name));
	        }
	        
	        if(emailLike!=null)
	        {
	        	predicates.addAll((Collection<? extends Predicate>) cb.equal(rooot.get("email"),emailLike));
	        }
	        cq.select(rooot).where((jakarta.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[0]));
	        return session.createQuery(cq).getResultList();
  	   
     }

}
