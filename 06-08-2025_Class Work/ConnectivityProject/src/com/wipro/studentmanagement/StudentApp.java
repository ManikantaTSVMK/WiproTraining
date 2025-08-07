package com.wipro.studentmanagement;

import java.util.Scanner;

import com.wipro.service.StudentDaoImpl;


public class StudentApp {

	public static void main(String[] args) {
		
		StudentDaoImpl stud = new StudentDaoImpl();
	Scanner sc = new Scanner(System.in);
	
	while(true) {		
		System.out.println("Enter your choise: -");
		System.out.println("1. Add, 2. View, 3. Update, 4. Delete By Id, 5. View By Id, 6. Exit");
		int n = sc.nextInt();
				switch(n) {
				case 1 -> stud.addStudent();
				case 2 -> stud.viewStudent();
				case 3 -> stud.updateStudent();
				case 4 -> stud.deleteStudentById();
				case 5 -> stud.viewStudentById();
				case 6 -> System.exit(0);
				default -> System.out.println("Enter a Valid Number");
				}
				
		
	}
	}

}