package com.example.oops.inheritances;

 class ClearanceItem extends Product  implements IDiscountable,ITaxable  {
	
	 /* If we are not extending the Product class then we are defining the fields name and price again*/
	//private String name;
	//private double price;
	
	

	public ClearanceItem(String name, double price) {
		super(name, price);    // for reusability we can call from base class constructor
		//this.name = name;
		//this.price = price;
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return (price - getDiscount()) * 0.05;
	}

	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return price*0.5;
	}
	
	public void printBill()
	{
		double discount = getDiscount();
		double tax = getTax();
		double finalPrice = price - discount +tax;
		System.out.println(" Actual Price : " + price + "|" + " Discounted amount: " + discount + "|" +  " Tax Amount : " + tax  );
		System.out.println("Congratulations ! the now final price is :" + finalPrice + " You have saved :" + (price-finalPrice) + " amount");
		
	}

}

public class MultipleInheritance
{
	public static void main(String[] args) {
		ClearanceItem item = new ClearanceItem("Speaker" , 4000);
		item.printBill();
	}
	
}

