package com.example.java8features;

import java.util.function.Predicate;

// Using the lambda expression anonymously you are defining and calling the abstract method of a functional interface
public class CalculatorOperations 
{

public static void main(String[] args) {
	
	
	Calculation add = (a,  b) -> a+b;
	Calculation sub = (a,b) -> a-b;
	
	System.out.println("Addition of two numbers : " + add.Calculator(45, 67));
	System.out.println("Substraction of two numbers : " + sub.Calculator(45, 67));
	
	
	
	
}
}
	
//	CalculatorOperations c  = new CalculatorOperations();
//	System.out.println(c.Calculator(34, 56));
//		
//}
//		@Override
//	public double (double a,double b) {
//		// TODO Auto-generated method stub
//		
//		return a+b; // statement
//		
//	}

	
	


