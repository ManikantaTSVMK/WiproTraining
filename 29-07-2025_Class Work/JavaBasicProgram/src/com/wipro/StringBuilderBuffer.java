package com.wipro;

import java.lang.System;

public class StringBuilderBuffer {
	
	// asynchronous / synchronous StringBuffer - It's synchronized and it's a thread safe
	public static void main(String[] args) {
		
	//	String cname = "Wipro";
	//	cname = cname + "Ltd"; // create  a new object ,, the original wipro 
		
		long start ,end;
		start = System.currentTimeMillis();
		String s = "";
		
		for(int i=0 ; i<500 ; i++)
		{
			
			s+="Some";
			System.out.println(System.identityHashCode(s));
		}
		end = System.currentTimeMillis();
		System.out.println("Total time taken using String " + (end-start));
		
		//String builder is mutable and fast --- It's not a thread safe
		//Efficient in appending to the same location and no new object will be created  
		start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for(int i=0 ; i<500 ; i++)
		{
			
			sb.append("Some");
			System.out.println(System.identityHashCode(sb));
		}
		end = System.currentTimeMillis();
		System.out.println("Total time taken using String Builder " + (end-start));
		
		
		//String Buffer is mutable but slow because of synchronization
		start = System.currentTimeMillis();
		StringBuffer sbuffer = new StringBuffer();
		for(int i=0 ; i<500 ; i++)
		{
			
			sbuffer.append("Some");
			System.out.println(System.identityHashCode(sbuffer));
		}
		end = System.currentTimeMillis();
		System.out.println("Total time taken using String Buffer " + (end-start));
		
		
		
	}
	

}
