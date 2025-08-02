package com.example.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Comparable is for natural ordering 
class Product implements Comparable<Product>
{
	String name ;
	double price ;
	double rating ;
	public Product(String name, double price, double rating) {
		super();
		this.name = name;
		this.price = price;   
		this.rating = rating;
	}
	
	// If we want to do the natural ordering by price
	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return Double.compare(this.rating , o.rating); // Collections.sort(list) -- internally it is calling compareTo method o each pair of object it return -1(less than) , 0(equal) , 1  (positive is greater)
	}

	// To string method is used to display the info of an object
	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", rating=" + rating + "]";
	}
	
	

}

public class ComparableSortingDemo {

	public static void main(String[] args) {
		List<Product> pr = new ArrayList<>();
		pr.add(new Product("Laptop" , 50000, 5));
		pr.add(new Product("Phone" , 20000, 3));
		pr.add(new Product("Tablet" , 10000, 1));
		pr.add(new Product("Mouse" , 500, 2));
		
		Collections.sort(pr); // uses comparable
		System.out.println(" Sorting elements by their Rating :" + pr);
		
         // For Custom Ordering you want to order by their name
		
		
	}

}

