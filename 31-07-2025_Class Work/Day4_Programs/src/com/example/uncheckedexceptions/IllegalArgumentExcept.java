package com.example.uncheckedexceptions;

import java.util.Scanner;

// When you try to convert a string into a number (like int , double etc. ) but the string is not properly formatted as a number 
public class IllegalArgumentExcept {
	
	static void validate(int age)
	{
		
		if(age<0) 
		{
		throw new IllegalArgumentException("Age cannot be negative");}
		else
			System.out.println("You have entered valid age :");
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number :");
		String input = sc.nextLine();
		
		System.out.println("Enter your age :");
		int age = sc.nextInt();
		
		try
		{
			
		int number = 	Integer.parseInt(input);
		System.out.println("No is :" + number);
		
		validate(age);
		
		Thread t = new Thread(()-> System.out.println("Thread is Running :"));
		t.start();
		t.start();  // IllegalThreadStateException
						
		}
		//IllegalArgumentException is further divided into two subclasses (NumberFormatException and IllegalThreadStateException)
		catch(NumberFormatException e)
		{
			System.out.println("Not a valid input integer  ");
		}
		catch(IllegalArgumentException ex)
		{
			
			System.out.println(ex.getMessage());
		}
//		catch(IllegalThreadStateException ex1)
//		{
//			
//			System.err.println("The same thread is already in a running state");
//		}
		
		

	}

}