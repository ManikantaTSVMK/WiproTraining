package com.wipro.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


// This file is for returning the properties from db.properties file
public class DBPropertyUtil {
	
	public static String getPropertyString(String filename)
	{
		// create an object of properties class to use the methods 
		Properties props = new Properties();
		// feature of java i.e. try with resources so that we need not close the fis resource 
		try(FileInputStream fis = new FileInputStream(filename))
		{
			props.load(fis);
				
			
		}
		catch(IOException e)
		{
			System.err.println("Failed to connect");
		}
		
	String url =	        props.getProperty("db.url");
	String user =	   props.getProperty("db.username");
	String password =	props.getProperty("db.password");
	
	System.out.println(url + "|" + user + "|" + password );
	return url + "|"  + user + "|" + password; 
		
	}

}