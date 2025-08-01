package com.example.oops.abstraction;


/*
 * can have instance variables
 * can have constructor
 * supports single inheritance 
 * */

/*
 * 1. Related classes share common behaviour or state
 * 2. If you want to prevent the direct instantiation while providing the partial implementation 
 * 3. when there is no agreement/ contract regarding 100% abstraction 
 * 
 * 
 * */

// In inheritance it is must to call base class constructor in child class

abstract class Product
{
	protected String id , name;
	protected double price;
	
	public Product(String id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public abstract double calculateTax();  //abstract method
	
	//common behaviour 
	public void printInfo()   // non-abstract method
	{
		
		System.out.printf("%s: %s @Rs%.2f (tax Rs%.2f)%n", id , name ,price , calculateTax());
		
		// %s it is for string value  ,%.2f  44545.76  , %n for a new line
	}

}

class DomesticProduct extends Product
{

	public DomesticProduct(String id, String name, double price) {
		super(id, name, price);
		
	}

	@Override
	public double calculateTax() {
		// TODO Auto-generated method stub
		return price * 0.05; // % 5 gst on domestic products 
	}
	
}


class InternationalProduct extends Product
{

	public InternationalProduct(String id, String name, double price) {
		super(id, name, price);
		
	}

	@Override
	public double calculateTax() {
		// TODO Auto-generated method stub
		return price * 0.18 + 450; // % 18 gst and some hidden charges like custom duty  on international products 
	}
	
}
public  class AbstractExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Product p1 = new DomesticProduct("D001" , "Laptop", 30000);
		Product p2 = new InternationalProduct("I001" , "Laptop", 30000);
		
		p1.printInfo();
		p2.printInfo();
	}

}

