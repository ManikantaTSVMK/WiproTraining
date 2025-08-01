package com.example.uncheckedexceptions;

public class NullPointerExcept {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
		String s = null;
		
		System.out.println("string length is " + s.length());
		}
		catch(NullPointerException ex)
		{
			
			System.out.println(ex.getMessage());
		}

	}

}