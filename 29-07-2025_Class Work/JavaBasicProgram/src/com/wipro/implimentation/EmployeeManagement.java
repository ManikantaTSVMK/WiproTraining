package com.wipro.implimentation;
import com.wipro.implimentation.Employee;
import java.util.Scanner;

class EmployeeManagement{
    static final int MAX=100;
    Employee[] employees=new Employee [MAX];
    int count=0;
    Scanner sc=new Scanner(System.in);

    public void menu(){
        while (true){
            System.out.println("\nEmployee Management");
            System.out.println("1.Add Employee");
            System.out.println("2.View All Employees");
            System.out.println("3.Search Employee by Name");
            System.out.println("4.Update Employee by ID");
            System.out.println("5.Delete Employee by ID");
            System.out.println("6.Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());
            switch (choice){
                case 1:
                    add();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    void add(){
        if (count < MAX){
            Employee e=new Employee();
            e.add(sc);
            employees[count++]=e;
            System.out.println("Employee added.");
        } else{
            System.out.println("Employee list full!");
        }
    }

    void view(){
        if (count==0){
            System.out.println("No employees to display.");
            return;
        }
        for (int i=0;i<count;i++){
            employees[i].view();
        }
    }

    void search(){
        System.out.print("Enter name to search: ");
        String searchName=sc.nextLine().trim().toLowerCase();
        boolean found=false;
        for (int i=0;i<count;i++){
            if (employees[i].name.toLowerCase().contains(searchName)){
                employees[i].view();
                found = true;
            }
        }
        if (!found){
            System.out.println("No employee found with name containing: "+searchName);
        }
    }

    void update(){
        System.out.print("Enter ID to update: ");
        int id=Integer.parseInt(sc.nextLine().trim());
        for (int i=0;i<count;i++){
            if (employees[i].id==id){
                System.out.println("Updating employee:");
                employees[i].add(sc);
                System.out.println("Employee updated.");
                return;
            }
        }
        System.out.println("Employee with ID "+id+" not found.");
    }

    void delete(){
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        for (int i=0;i<count;i++){
            if (employees[i].id==id){
                for (int j=i;j<count-1;j++){
                    employees[j]=employees[j+1];
                }
                employees[--count]=null;
                System.out.println("Employee deleted.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }
    public static void main(String[] args) {
        EmployeeManagement em = new EmployeeManagement();
        em.menu();
    }
}


