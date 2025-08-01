package com.example.exceptions;

import java.util.Scanner;

// When you try to convert a string into a number (like int , double etc. ) but the string is not properly formatted as a number 
public class NumberFormattingExcept {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number :");
		String input = sc.nextLine();
		
		try
		{
			
		int number = 	Integer.parseInt(input);
		System.out.println("No is :" + number);
		
		
		}
		catch(Exception e)
		{
			System.out.println("Not a valid input integer  ");
		}
		
		

	}

}