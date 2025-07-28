package com.wipro;

import java.io.File;
import java.util.Scanner;

// class is a blue print or a template that contains data member (attributes) or member functions
class Employee
{
	//stored in heap 
	int empId ;
	String name;
	int age;
	String designation;
	
	// this is a special type of method where class name and constructor name are same to initiaze the
	// memory of class instances or non static variables
	// Stored in Method Area
	//Void does not return anything .. 
	// No args constructor
//	Employee()
//	{
//		
//		
//	}
	//Args Based constructor
	Employee(int empId , String name , int age , String designation)
	{
		 // this.empId is used to refer the class or instance variable
		 // this resolves the ambiguity between the instance variable and local variables if names are same
		 //this also represents as an object of current class
		
		this.empId = empId;
		this.name = name;
		this.age = age;
		this.designation = designation;
		
		System.out.println(empId + " " + name + " " + age + " " + designation );
		
		
	}
	
	//this is  a member function or a method of a class - functionality
	public static void Display(String organization)
	{
		
		System.out.println("You are from :" + organization);
		
	}
	
	//this is  a member function or a method of a class - functionality
	public static int Input(int a , int b)
	{
		int result = a+b;
		
		//System.out.println("The result is :" + result);
		Display("Wipro");
		return result;
		
	}




}


 public class ClassAndObject {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Scanner is used to take input from the console  wether it's from keyboard that is
		//System.in defines or tell to the scanner where to read from and files then it is new File("bac.txt")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your id :");
		
		int id = sc.nextInt();
		System.out.println("Enter your name :");
		
		String name = sc.next();
		System.out.println("Enter your age :");
		
		int age = sc.nextInt();
		System.out.println("Enter your designation :");
		
		String designation = sc.next();
		
		Employee employee = new Employee(id , name ,age, designation);
		System.out.println(employee.Input(23,45));
		employee.Display("Wipro");

	}

}

 
 /*
  * Constructor rules are given below:
  * 1. class name as same as constructor name
  * 2. No return type
  * 3. It can be parameterized or overloaded and has an access specifier
  * */
  
