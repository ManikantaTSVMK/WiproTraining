package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	static Calculator c = null;
	static int i = 0;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	 c = new Calculator();
	 System.out.println("Calculator Class Object is initialized");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	c = null;
	 System.out.println("Calculator Class Object is null");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		
		i++;
		System.out.println("Test case : " + i + " is passed");
	}

	@Test
	void testAdd() {
		 int a =10;
		 int b=30;
		int result =  c.add(a, b); // this will be your actual output (you are getting the result after returning from the calculator class)
	
		assertEquals(40 , result); // 40 will be your expected output based on dummy data you have provided 
		
	}
	
	@Test
	void testMultiply() {
		 int a =10;
		 int b=30;
		int result =  c.multiply(a, b); // this will be your actual output (you are getting the result after returning from the calculator class)
	
		assertEquals(300 , result); // 300 will be your expected output based on dummy data you have provided 
		
	}
	
	@Test
	void testDivide() {
		 int a =10;
		 int b=0;
		//int result =  c.divide(a, b); // this will be your actual output (you are getting the result after returning from the calculator class)
	
		assertThrows(IllegalArgumentException.class,() ->
		{ c.divide(a, b); // 5 will be your expected output based on dummy data you have provided 
		});
	}

}