package com.wipro;



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
	//Void does not return anything
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
	void Input()
	{
		
		System.out.println("Input method called");
		
	}

	//this is  a member function or a method of a class - functionality
	void Display()
	{
		
		System.out.println("Output method called");
		
	}


}


 public class ClassAndObject {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		Employee employee = new Employee(102 , "Jatin" ,40 , "BusinessHead");
		employee.Input();
		employee.Display();

	}

}
