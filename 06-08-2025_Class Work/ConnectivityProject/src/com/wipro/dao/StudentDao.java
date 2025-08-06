package com.wipro.dao;


public interface StudentDao {
	
	
	// abstract methods
	public void addStudent(); // insert into student(name,age) values(?,?);
	public void viewStudent(); // select * from student
	public void updateStudent(); // update student set age=? where id=?
	public void deleteStudentById(); // delete from student where id=?
	public void viewStudentById(); // select * from student where id=?
}