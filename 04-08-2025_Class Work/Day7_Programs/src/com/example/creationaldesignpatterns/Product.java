package com.example.creationaldesignpatterns;



// An utility builder class for an immutable  product

final class Product {
	
	private final String name;
	private final double price;
	private final String configuration;
	
   // getting an object on ProductBuilder passed through Product class constructor using Build()
	private Product(ProductBuilder pB) {
		
		this.name = pB.name;
		this.price = pB.price;
		this.configuration = pB.configuration;
	}

	public static class ProductBuilder
	{
		
		private String name; // cannot access it directly  , we will give setter method to them 
		private double price;
		private String configuration;
		
		public ProductBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public ProductBuilder setPrice(double price) {
			
			this.price = price;
			return this;
		}
		
			public ProductBuilder setConfiguration(String configuration) {
			
			this.configuration = configuration;
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