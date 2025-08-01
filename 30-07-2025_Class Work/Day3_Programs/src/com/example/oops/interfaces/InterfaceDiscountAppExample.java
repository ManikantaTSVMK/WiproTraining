package com.example.oops.interfaces;

// Main Program of Interface creating an array of Discountable Interface and storing the object of 
// two classes (SeasonalProduct.java and ClearanceProduct.java) implementing the abstract method of an Interface
public class InterfaceDiscountAppExample {

	public static void main(String[] args) {
		
		// As we know that we cannot create an object of Interface ,
		//so here we are creating an array for allocating only a space of n for holding the references to an objects of those classes who all are implementing the Discountable interface 
		
		Discountable[] discounts = new Discountable[2];
		
		// for eg: String[] s = new String[2];
		
		      
		
		discounts[0] = new SeasonalProduct("Laptop" , 3000);
		discounts[1] = new ClearanceProduct("Old SmartPhone" , 5000);
		
		System.out.println("Discount Summary Report");
		
		for(Discountable d : discounts)
		{
			
			if(d instanceof SeasonalProduct sp) sp.show();
			else if(d instanceof ClearanceProduct cp) cp.show();
			
		}

	}

}
