package com.wipro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.wipro.dao.StudentDao;
import com.wipro.util.DBConnUtil;

public class StudentDaoImpl implements StudentDao {
	static Scanner sc = new Scanner(System.in);
	
	Connection conn = DBConnUtil.getConn();

	@Override
	public void addStudent() {
		
		// query 
		String query = "insert into student(name, age) values(?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, "Ranjan");
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
				
				System.out.println("Id :" + id + " " + " Name : " + name + " Age :" + age );
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateStudent() {
		String query = "Update student set name = ?, age = ? where id = ?";
		try {
//			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the name:- ");
			String name = sc.next();
			
			System.out.println("Enter the age:- ");
			int age = sc.nextInt();
			
			System.out.println("Enter the id:- ");
			int id = sc.nextInt();
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setInt(3, id);
			
			int i = ps.executeUpdate();
			
			if (i>0)
			{
				System.out.println("Record is added");
				StudentDaoImpl ss = new StudentDaoImpl();
				ss.viewStudent();
			}
			else 
			{
				System.out.println("Record is not added");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void deleteStudentById() {
		String query = "delete from student where id = ?";
		
		try {
			System.out.println("Enter the Id to Delete the Record...");
			int id = sc.nextInt();
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			
			
			if (i>0)
			{
				System.out.println("Record deletes Succesfully!");
				StudentDaoImpl ss = new StudentDaoImpl();
				ss.viewStudent();
			}
			else {
				System.out.println("Record not exist...");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void viewStudentById() {
	    String query = "SELECT * FROM student WHERE id=?";
	    try {
	        System.out.print("Enter student id to view: ");
	        int id = sc.nextInt();

	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            System.out.println(
	                "Id: " + rs.getInt("id") +
	                " Name: " + rs.getString("name") +
	                " Age: " + rs.getInt("age")
	            );
	        } else {
	            System.out.println("No student found with given id.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	

}