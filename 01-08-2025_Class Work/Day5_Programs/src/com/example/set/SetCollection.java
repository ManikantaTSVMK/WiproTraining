package com.example.set;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.example.list.Employee;

/* Set  -- unique element and no Duplicate elements , unordered  */



public class SetCollection {
 
 private static Set<Employee> employee= new HashSet<>();

 private static Scanner sc  = new Scanner(System.in);

 
 public static void main(String[] args) {
  while(true)
  {
   System.out.println("\n1.Add , 2.Delete , 3. Search , 4. View All , 5. Add Employee at a Position 6. Sort  7. Exit");
   int choice = sc.nextInt();
   switch(choice)
   {
   case 1  -> addEmployee();
   case 2 -> deleteEmployee();
   case 3 -> searchEmployee();
   case 4-> viewAll();
   case 5 -> addEmployeeAtPosition();
   case 6 ->  sortEmployee();
   case 7 -> Exit();
   
   }
  }
 }
 
 static void addEmployee() {
  
  System.out.println("Enter id  , Name , salary");
  int id=sc.nextInt();
  String name= sc.next();
  double salary = sc.nextDouble();
  employee.add(new Employee(id,name,salary));
  
  
 }
 static void viewAll() {
  System.out.println("Employee Data\n");
  for(Employee e: employee) {
   System.out.println(e);
  }
 }
 
 static void deleteEmployee() {
	    System.out.print("Enter Id to Delete: ");
	    int id = sc.nextInt();

	    boolean found = false;
	    for (Employee emp : employee) {
	        if (emp.getId() == id) {
	           employee.remove(emp);
	            System.out.println("Employee with ID " + id + " removed.");
	            found = true;
	            break; 
	        }
	    }

	    if (!found) {
	        System.out.println("Employee with ID " + id + " not found.");
	    }
	}

 
 static void searchEmployee() {
	    System.out.print("Enter Id to Search: ");
	    int id = sc.nextInt();

	    boolean found = false;
	    for (Employee emp : employee) {
	        if (emp.getId() == id) {
	           
	            System.out.println("Employee with ID found :" + emp);
	            found = true;
	            break; 
	        }
	    }

	    if (!found) {
	        System.out.println("Employee with ID " + id + " not found.");
	    }
	}

 
static void addEmployeeAtPosition() {
	 
	 System.out.println("Enter the position where you want to add the employee:");
	 int position = sc.nextInt();
	 if(position>=0 && position<=employee.size())
	 {
	 System.out.println("Enter id  , Name , salary");
	 int id=sc.nextInt();
	 String name= sc.next();
	 double salary = sc.nextDouble();
	// employee.add(position , new Employee(id,name,salary));
	 }
	 
	 else
		 System.out.println("Invalid Position");
	 
 }
 
static void sortEmployee()
{
	
}
static void Exit()
{
	System.exit(0);
	}
 
}
 
 



