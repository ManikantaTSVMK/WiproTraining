Jdbc-

 java (BackEnd) ---- DataBase
 
 wireless -
 Data Cable
 
 To connect with third party(hardware) application you need some drivers
 
 1. We need driver to connect with your database ( type1(DSN) , type2, type3 , type4)
 DSN -- on every pc you have to create a dsn which is not feasible , was time cosuming and not safe
 type2 - provided by native(Thirdy party) database libraries which needs to be installed on each client machine, lot of dependency it was there
 type3 - Network specific protocol(drivers are there on network) -platform independent
 type4 - pure java libraries -- For this first we need to add a jar file
 2. libraries --  we need to set in our class path
 go to build path -> configure build path - libraries tab -> select classpath -> select add external jar 
 
 3. we need to create a folder as resources and then add a file in it with the name as db.properties and 
 write the mysql configuration as below:
db.url=jdbc:mysql://localhost:3306/student_database
db.username=root
db.password=root

4. create a architecture of your project 
dao -- data access object -- interface -  crud operation methods to declare
service-- crud operation logic of methods you declared in interface (implementation class)
entity --  entity / model class
studentmanagement -- Main ( Entry point)
util -- utility file to connect with database 

5. To get the properties from db.properties we are creating DBPropertyUtil.java file


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


6. now create DBConnUtil to getPropertyString(String filename)

package com.wipro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This file is for getting and creating the mysql connection // based on singleton pattern
public class DBConnUtil {
	
	private static Connection conn ;
	
	// getter method to get the connection
	public static Connection getConn() 
	{
		if(conn == null)
			try
		{
			String connectionString = DBPropertyUtil.getPropertyString("resources/db.properties");
			String[]connectionCredentials =  connectionString.split("\\|");
			String url = connectionCredentials[0];
			String user = connectionCredentials[1];
			String password = connectionCredentials[2];
			
			
			conn = DriverManager.getConnection(url,user,password);
			
			System.out.println("SQL Connected");
		}
			catch(SQLException e)
		{
				System.err.println("Failed to establish connection");
		}
		
		return conn;
	}
	

}