package com.company.springcore.javabasedconfig;


public class Employee {
	
	private Address addr;

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public Employee(Address addr) {
		super();
		this.addr = addr;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void welcomeEmployee()
	{
		
		System.out.println("This is the example of java based config to access address of employee");
	}

}