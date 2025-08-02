package com.example.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

//Let's if we want to log messages from different perspective like info,  warning , exception message we use 
// Logging framework 

public class LoggerDemoExample {

	private static final Logger logger = Logger.getLogger(LoggerDemoExample.class.getName());
	
	
	public static void main(String[] args) {
	
	//	logger.info("Application started");
		logger.warning("Warning Message where memory or efficiency is high");
		logger.severe("Unhandled exception occured");

		try
		{
			
			int result = divide(10,2);
		//	logger.info("Result" + result);
			
			System.out.println("Result" + result);
			
		}
		catch(ArithmeticException e)
		{
			
			logger.log(Level.SEVERE, "Division Error : " + e.getMessage());
		}
		logger.info("Application over");
		
	}
	
	static int divide(int a  , int b)
	{
		return a/b;
	}

}


