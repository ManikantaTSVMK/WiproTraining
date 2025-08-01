package com.example.oops.inheritances;


abstract class Product
{
	//sharing common state in Multiple and Hybrid Inheritance
	protected String name;
	protected double price;
	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public void showDetails()
	{
		
		System.out.println(name + " : Rs. " + price);
	}


}

class Electronics extends Product
{

	public Electronics(String name, double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}
	
	public void showWarranty()
	{
		System.out.println( name + " This product comes with 2 years of warranty");
	}
	
	

}

class Grocery extends Product
{

	public Grocery(String name, double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}
	

	public void showExpiry()
	{
		System.out.println( name + " This product will expire in 10 months");
	}
	
	

}
