package com.wipro.implimentation;

import java.util.Scanner;

class Employee {
    int id;
    String name;
    String position;
    double salary;

    // Add employee details
    public void add(Scanner sc){
        System.out.print("Enter ID: ");
        id=Integer.parseInt(sc.nextLine().trim());
        System.out.print("Enter Name: ");
        name=sc.nextLine().trim();
        System.out.print("Enter Position: ");
        position=sc.nextLine().trim();
        System.out.print("Enter Salary: ");
        salary=Double.parseDouble(sc.nextLine().trim());
    }

    // View employee details
    public void view() {
        System.out.println("ID: "+id+",Name: "+name+",Position: "+position+",Salary: "+salary);
    }
}
