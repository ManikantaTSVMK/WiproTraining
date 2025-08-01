package com.example.oops.inheritances;

public class HierarchicalInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Electronics e = new Electronics("Laptop" , 56000);
		Grocery g = new Grocery("Shampoo" , 500);
		
		e.showDetails();e.showWarranty();
		g.showDetails();g.showExpiry();
	}

}