package com.example.uncheckedexceptions;



public class DivideByZeroException {
	
	static int divide(int a , int b)
	{
		
		return a/b;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
		int result = divide(40,0);
		System.out.println("Result after division: "+ result);
		}
		
		
		catch(ArithmeticException ex)
		{
			System.out.println("Some Arithmetic Division Exception is occured ! check the logic");
		}
		
		finally
		{
			
			System.out.println("Division done :");
		}
		
		
		
		

	}

}