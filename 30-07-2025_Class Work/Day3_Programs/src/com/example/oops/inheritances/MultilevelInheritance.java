package com.example.oops.inheritances;


class User
{
	protected String name;

	public User(String name) {
		super();
		this.name = name;
	}
	
	public void login()
	{
		
		System.out.println("Welcome " + name + " you have logged in !");
	}


}

class Customer extends User
{

	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	

	public void browseProducts()
	{
		System.out.println("Hi! " + name + " you may browse the products. ");
	}


}

class PremiumCustomer extends Customer
{

	public PremiumCustomer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	

	public void getCashBack()
	{
		System.out.println("Hi! " + name + " Congrats ! cash back is added  to you wallet");
	}


}


public class MultilevelInheritance {
	
	public static void main(String[] args) {
		
		PremiumCustomer pc = new PremiumCustomer("Niti");
		pc.login();
		pc.browseProducts();
		pc.getCashBack();
	}

}
