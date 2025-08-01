package com.example.list;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* List -- Ordered , allows Duplicate elements */


// If you are insert/ deleting the employees frequently in middle then use LinkedList

// and if you are appending and searching / view / sorting  using frequently then use ArrayList
public class ArrayListEmployee {
 
 private static List<Employee> list= new ArrayList<>();
 private static List<Employee> list1= new LinkedList<>();
 private static Scanner sc  = new Scanner(System.in);
 private static Employee e = new Employee();
 
 public static void main(String[] args) {
  while(true)
  {
   System.out.println("\n1.Add , 2.Delete , 3. Search , 4. View All , 5. Add Employee at Position  6. Exit");
   int choice = sc.nextInt();
   switch(choice)
   {
   case 1  -> addEmployee();
   case 2 -> deleteEmployee();
   case 3 -> searchEmployee();
   case 4-> viewAll();
   case 5 -> addEmployeeAtPosition();
   case 6 -> Exit();
   
   }
  }
 }
 
 static void addEmployee() {
  
  System.out.println("Enter id  , Name , salary");
  int id=sc.nextInt();
  String name= sc.next();
  double salary = sc.nextDouble();
  list.add(new Employee(id,name,salary));
  
  
 }
 static void viewAll() {
  System.out.println("Employee Data\n");
  for(Employee e: list) {
   System.out.println(e);
  }
 }
 
 static void deleteEmployee() {
	    System.out.print("Enter Id to Delete: ");
	    int id = sc.nextInt();

	    boolean found = false;
	    for (Employee emp : list) {
	        if (emp.getId() == id) {
	            list.remove(emp);
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
	    for (Employee emp : list) {
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
	 if(position>=0 && position<=list.size())
	 {
	 System.out.println("Enter id  , Name , salary");
	 int id=sc.nextInt();
	 String name= sc.next();
	 double salary = sc.nextDouble();
	 list.add(position , new Employee(id,name,salary));
	 }
	 
	 else
		 System.out.println("Invalid Position");
	 
 }
 
static void Exit()
{
	System.exit(0);
	}
 
}
 
 




