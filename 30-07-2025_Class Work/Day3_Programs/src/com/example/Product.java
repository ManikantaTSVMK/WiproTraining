package com.example;



// An utility builder class for an immutable  product

final class Product {
	
	private final String name;
	private final double price;
	
	
   // getting an object on ProductBuilder passed through Product class constructor using Build()
	private Product(ProductBuilder pB) {
		
		this.name = pB.name;
		this.price = pB.price;
	}

	public static class ProductBuilder
	{
		
		private String name; // cannot access it directly  , we will give setter method to them 
		private double price;
		
		public ProductBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public ProductBuilder setPrice(double price) {
			
			this.price = price;
			return this;
		}
		
		
		public Product build()
		{
			
			return new Product(this); // return ProductBuilder class object
		}
		
}
	public void show() 
	{
		System.out.println("Product :" + name + " Price :" + price);
	}

	
}



