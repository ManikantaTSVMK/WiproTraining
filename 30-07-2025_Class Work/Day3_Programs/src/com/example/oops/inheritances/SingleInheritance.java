package com.example.oops.inheritances;


class Order
{
	//These fields are accessible in Child class only due to protected
	protected String orderId;
	protected double amount;
	
	//These fields are accessible in Child class because of constructor which is public or protected in base class and calling using super keyword in child class
	/*private String orderId;
	private double amount;*/
	
	protected Order(String orderId, double amount) {
		super(); // calling the object class constructor
		this.orderId = orderId;
		this.amount = amount;
	}
	
//	private void display()
//	{
//		System.out.println("Order Id :" + orderId + " and Amount : Rs . " + amount );
//	}
//	
	protected void display()
	{
		System.out.println("Order Id :" + orderId + " and Amount : Rs . " + amount );
	}
	
}

class OnlineOrder extends Order
{
	private String trackingNumber;

	/* If data members or fields are private in base class then we are able to access indirectly 
	 * using public or protected constructor defined in base class just like you are accessing the private members via public getter & setters 
	 * methods in encapsulation */
	public OnlineOrder(String orderId, double amount, String trackingNumber) {
		super(orderId, amount);
		this.trackingNumber = trackingNumber;
		
	}
	
	
	public void trackOrder()
	{
	//	display(); // directly cannot access it it is private in base class
		
		display(); // we can access because it is protected or public in base class
		System.out.println("Tracking Number :" + trackingNumber);
		
	}

}


public class SingleInheritance {
	
	public static void main(String[] args) {
		OnlineOrder o = new OnlineOrder("Order101" , 3500 , "TR0109");
		o.trackOrder();
	}

}
