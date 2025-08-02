package com.example.java8features;



//@FunctionalInterface : It is an interface where we have exactly one abstract method and may or may not have 
//default or static methods 

@FunctionalInterface
interface Calculation{
	
	//One abstract method
	int Calculator(int a  ,  int b);
	
	
	default void subtract()
	{
		
		
	}
	static void delete()
	{
		
		
	}
	
	
	
}

