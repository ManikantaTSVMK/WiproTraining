package com.wipro;
/*
public class Student {
    int id;
    String name;
    int[] marks;
   
    public Student(int id, String name, int[] marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.print("Marks: ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] studentMarks = {85, 90, 78}; 
        Student student = new Student(1, "Manikanta", studentMarks);
        student.display();
    }
}*/

import java.util.Scanner;

public class Student {
	
	
	int id ;
	String name;
	int[][] marks;
	
	
		public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Constructor
	public Student(int id, String name, int[][] marks) {
		super();
		this.id = id;
		this.name = name;
		this.marks = marks;
	}

	public void displayInfo()
	{
		System.out.println("Student Id :" + id);
		System.out.println("Student Name :" + name);
		System.out.println("Student Marks :" );
		for(int i = 0; i < marks.length; i++) {
			for(int j = 0; j < marks[i].length; j++) {
				System.out.print(marks[i][j] + " ");
			}
		}
		System.out.println();
		
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("How many students you want to add in a Batch");
		int batchSize = sc.nextInt();
		Student[] students = new Student[batchSize];
		
		for(int i= 0 ; i<students.length ; i++)
		{
			System.out.println("Enter student Id: ");
			int id = sc.nextInt();
			
			System.out.println("Enter student name: ");
			String name = sc.next();
			
			System.out.println("Enter No Of Subjects: ");			
			int n = sc.nextInt();
			int numSubjects = n; 
			int[][] marks = new int[numSubjects][1]; 
			
			System.out.println("Enter marks for " + numSubjects + " subjects");
			for(int j=0;j<numSubjects;j++)
			{
				System.out.println("Subject " + (j+1) + " : ");
				marks[j][0] = sc.nextInt();
			}
			
			students[i] = new Student(id,name,marks);
		}
		
		System.out.println("Students details are given below : " );
		for(Student s : students)
		{
			s.displayInfo();
		}
		sc.close();
	}

}
