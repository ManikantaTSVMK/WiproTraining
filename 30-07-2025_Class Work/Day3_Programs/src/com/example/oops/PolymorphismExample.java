package com.example.oops;


// E-commerce  -- users are paying using either credit card  ,  cod  ,  upi(Google Pay , PhonePe etc.)
// vendor specific implementation --  Abstract or Interface -- Abstraction
// Payment processing -- polymorphism
// user id  , password  , roles  -- Encapsulation(Data Validations)

// So payment class in which we have a paymentProcessing method which is common for all the vendors

//Base/Parent/Super Class which Amazon has created 
 class PaymentMethod
{
	 // Example of Overloading
	public void processPayment(double amount)
	{
		System.out.println("Processing payment is done of Rs. " + amount);
	}
	
	public void processPayment(int amount , int gstNumber)
	{
		System.out.println("Processing payment is done of Rs. " + amount);
	}
}

 // Child/Sub/Derived Class overriding the processPayment Method
class Zara extends PaymentMethod
{
	
	class CreditCard extends PaymentMethod
	{
		
//		public void processPayment(double amount)
//		{
//			System.out.println("Payment is done via Credit Card :" + amount);
//		}
		
	}
	
	class Upi extends PaymentMethod
	{
		
		public void processPayment(double amount)
		{
			System.out.println("Payment is done via Upi :"+ amount);
		}
		
	}



}
 

// Child/Sub/Derived Class
class Nike extends PaymentMethod
{
	
	class CreditCard extends PaymentMethod
	{
		
		public void processPayment(double amount , int gstNumber)
		{
			System.out.println("Payment is done via Credit Card :" + amount);
		}
		
		public void processPayment(double amount )
		{
			System.out.println("Payment is done via Credit Card without GST :" + amount);
		}
		
		
	}
	
	class Upi extends PaymentMethod
	{
		
		public void processPayment(double amount , int gstNumber)
		{
			System.out.println("Payment is done via Upi  :" + amount);
		}
		
	}



}
 
public class PolymorphismExample {
	
	public static void main(String[] args)
	
	{
		
		
		Zara zara = new Zara();
		
		
		Nike nike = new Nike();

        Zara.CreditCard cc = zara.new CreditCard(); 
        cc.processPayment(1000);

        Zara.Upi upi = zara.new Upi();
        upi.processPayment(500);
		
        
        Nike.CreditCard cc1 = nike.new CreditCard(); 
        cc1.processPayment(6000,346657);

        Nike.Upi upi1 = nike.new Upi();
        upi1.processPayment(700,678687);
		
        
        
        
//		PaymentMethod p = z.new CreditCard();
//		p.processPayment(10000);
//		PaymentMethod p1 = z.new Upi();
//		p1.processPayment(600);
		
		
	}

}
