package com.example.behavioraldesignpatterns;

import java.util.Scanner;

public class ProductMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Which product you are looking for ");
		String product = sc.next();
		
		System.out.println(" Product configuration");
		String productconf = sc.next();
		//Through the ProductBuilder Class we are making Product class as Mutable
		
		Product p = new Product.ProductBuilder()
				 .setName(product)
				 .setConfiguration(productconf)
				 .setPrice(45000)
				 .build();
		
		
		p.show();	
		
//		Product p1 = new Product.ProductBuilder()
//				.setName(product)
//				.setPrice(45000.00)
//				.build();
//		
//		p1.show();

	}	

}

