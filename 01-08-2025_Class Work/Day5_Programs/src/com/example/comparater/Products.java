package com.example.comparater;

class Products 
{
	String name ;
	double price ;
	double rating ;
	
	public Products(String name, double price, double rating) {
		super();
		this.name = name;
		this.price = price;   
		this.rating = rating;
	}
	
	
	// To string method is used to display the info of an object
	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", rating=" + rating + "]";
	}
}
