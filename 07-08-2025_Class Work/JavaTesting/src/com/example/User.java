package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	
	
   private static final String 	db_url="jdbc:mysql://localhost:3306/student_database";
	   private static final	String db_username="root";
			   private static final String	db_password="Mani@2002";
			   
			   public boolean isValidUser(String username , String password)
			   {
				   if(password == null || password.length()>=8){
					System.out.println("Password is too short");   
					   return false ;
				   }
				 
				   
				   String query = " select count(*) from userlogin where username=? and password=?";
				   
				   try
				   {
					  Connection conn = DriverManager.getConnection(db_url,db_username, db_password);
					  
					  PreparedStatement ps = conn.prepareStatement(query);
					   
					  ps.setString(1, username);
					  ps.setString(2, password);
					   
					  ResultSet rs = ps.executeQuery();
					  if(rs.next())
					  {
						  return rs.getInt(1) > 0;
					  }
				   }
				   
				   
				   catch(SQLException e)
				   {
					   e.printStackTrace();
				   }
				   
				   return false;
			   }

}
