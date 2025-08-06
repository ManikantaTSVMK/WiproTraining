package com.wipro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wipro.dao.StudentDao;
import com.wipro.util.DBConnUtil;

public class StudentDaoImpl implements StudentDao {
	
	Connection conn = DBConnUtil.getConn();

	@Override
	public void addStudent() {
		
		// query 
		String query = "insert into student(name, age) values(?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, "Nitin");
			ps.setInt(2, 44);
			
			int i = ps.executeUpdate();
			
			if (i>0)
			{
				System.out.println("Record is added");
			}
			else 
			{
				System.out.println("Record is not added");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewStudent() {
		
		String query2 = "Select * from Student";
		
		try {
			Statement s = conn.createStatement();
			// it is returning in tabular format so we are using result set interface
			ResultSet rs  = s.executeQuery(query2);
			
			// we are placing the pointer on each row one by one
			while(rs.next())
			{
				int id  = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				System.out.println("Id :" + id + " " + " Name : " + " " + "Age :" + age );
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateStudent() {
		
		
	}


	@Override
	public void deleteStudentById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewStudentById() {
		// TODO Auto-generated method stub
		
	}

	

}