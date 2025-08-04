package com.example.threadclass;

public class WithoutThreadExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int localValue = 0;
		int sharedValue  =0;
		
		for (int i= 1 ; i<=3 ;i++)
		{
			localValue++;
			sharedValue++;
			System.out.println("at i :" + i + "the localValue is :" + localValue + " and sharedValue is  :" + sharedValue);
		}

		System.out.println("the final sharedValue is : " + sharedValue);
	}

}

