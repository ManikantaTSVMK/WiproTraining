package com.example.list;

public class Employee {
 private int id;
 private String name;
 private double Salary;


 public Employee(int id, String name, double salary) {
  super();
  this.id = id;
  this.name = name;
  Salary = salary;
 }

 public Employee() {
}

public int getId() {
	return id;
}


public String getName() {
	return name;
}


public double getSalary() {
	return Salary;
}


@Override
 public String toString() {
  return "employe [id=" + id + ", name=" + name + ", Salary=" + Salary + "]";
 }
 
}
