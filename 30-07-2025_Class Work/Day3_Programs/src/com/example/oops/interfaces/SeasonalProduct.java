package com.example.oops.interfaces;

public class SeasonalProduct implements Discountable{
	
	private String name;
	private double price;
	
	public SeasonalProduct(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	@Override
	public double calculateDiscount() {
		// TODO Auto-generated method stub
		return price*0.10;
	}

	
	public void show()
	{
		
	//	System.out.println("Seasonal Discount : "+name+price+calculateDiscount());
		
   System.out.printf("Seasonal : %s | Price: Rs. %.2f | Discount: Rs. %.2f%n" ,name,price,calculateDiscount());
	}




	
	
	

}
