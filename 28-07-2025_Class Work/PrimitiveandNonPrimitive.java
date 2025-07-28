package com.wipro;



public class PrimitiveandNonPrimitive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Primitive Data Types  ---  Basic Built in types that stores values directly in memory 
		// Here we can single value as well as single type of data will be stored
		
	/*Higher to lower size  then explicit casting is required also called it as
	 * downward casting or Narrowing Casting
	 * 
	 * short s =10;
	 * byte b =(byte)s; // casting is required
	 */
		byte b=10 ;		//1 byte - 8 bits
		short s =20;	//2 bytes - 16 bits
		int  a =20; 	//4 bytes - 32 bits
		long l =4556L;	 // 8 bytes - 64 bits
		float f= 45.50f;	//4 bytes - 32 bits
		double d = 45.78;	// 8 bytes - 64 bits
		char c ='A';		// 2 bytes - 16 bits 
		boolean flag = true ;	//1 bit 
		
	
	
		/*Lower to Higher size  then implicit casting takes place automatically and also called it as
		 * Upward casting or Widening Casting
		 * 
		 * byte s =10;
		 * short b =a; // No casting is required
		 */	
		
// Use of primitive data types is more 
// critical in performance based applications
// like calculations , counters , flags 
		
// Limitations are that they do provide built-in 
// methods (eg char cannot call .toUpperCase())
		
		
	//NON- Primitive Data types -- Complex types that store the references(memory addresses)to an object
	//(variable Size)	 // and somewhere also stores the different type of data and multiple data can be stored
	// String , Array , Class , Interface , Records , Enumeration
	String firstName = "Niti Dwivedi"; 	 // String is immutable
	int[]  arr = {3,4,5};
	//Any noun -- Class is a blueprint or a template which is a non-primitive data type which 
	// declares the different types of data 
	class Employee {String name ;int age; int empId;}
	{
	//To access the class we use object of a class
	Employee e = new Employee();
	System.out.println(e.name);
	}
	//Interface is also a blueprint of all abstract methods to declare it 
	interface Swiggy {void order();}
	
	//Enum is a special class that represents a group  of named constants (unchangeable, predefined value)
	enum Day
	{
		Monday , Tuesday , Wednesday , Thurday , Friday ,Saturday , Sunday
	}
     // To access these values 
	Day today =  Day.Monday;
	
	//It's introduced in Java 16
	// Record - It is a compact, immutable data class that automatically provides getter, toString(),
	//equals(), hashCode(), constructor
	
	 record User(String name ,  int age) {}
	 //How to acces
	  User user = new User("Niti" , 34);
	}
	
	
	

}
