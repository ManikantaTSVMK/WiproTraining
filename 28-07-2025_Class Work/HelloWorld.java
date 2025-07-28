package com.wipro;

import java.util.Scanner;

//Milestone-1 --- Arrays & Strings  ( Syntax and Naming conventions)
//Capstone -- 20 rubrics(front end / BackEnd // Syntaxes , NC , Comments )
//Concrete class
public class HelloWorld {

	//Entry Point of your Java Program
	//static is a keyword which can be called by the compiler without any object 
	//void : this method does not return any value
	// main : the name of methods recognized by the JVM as an entry point
	//String[] args : String is a class and [] means an array so it's an array of strings that can receive command line arguments
	public static void main(String[] args) {
		// is used to print output to the console then followed by a new line character.
		//System is a class , out is a reference variable , println is a method of PrintStream class
		// another reference variable of a system class is in and err
		System.out.println("Hello Java World!");
		
		//To take input
		Scanner sc = new Scanner(System.in);
		
		String var = sc.next();
		
		//To print errors
		System.err.print("Error in your program");
		
    
	}

}
