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