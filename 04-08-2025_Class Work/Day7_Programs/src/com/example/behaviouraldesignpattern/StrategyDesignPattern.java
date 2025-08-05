package com.example.behaviouraldesignpattern;

import java.util.Scanner;

//different strategies that hr is making role wise , encapsulating each and making them interchangeable 

//Payment method selection in checkout

interface PaymentStrategy 
{
void pay(double amount)	;
}

class CreditCardStrategy implements PaymentStrategy 
{

	@Override
	public void pay(double amount) {
		System.out.println(" This much amount is paid :" + amount + " via credit card");
		
	}
	

}

class UpiStrategy implements PaymentStrategy 
{

	@Override
	public void pay(double amount) {
		System.out.println(" This much amount is paid :" + amount + " via Upi");
		
	}
	

}

class Checkout
{
	
void checkout(double amount , PaymentStrategy p)
{
p.pay(amount);	
}
}


// Factory class is responsible for providing the objects 
class PaymentFactory{
	
	public static PaymentStrategy getPayment(String type)
	{
		
		return switch(type)
				{
					case "credit" -> new CreditCardStrategy();
					case "upi" -> new UpiStrategy();
					default -> throw new IllegalArgumentException("Unexpected value: " + type);
				};
	}
	
	
	
}
public class StrategyDesignPattern {

	public static void main(String[] args) {
		
		Checkout c = new Checkout();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the payment method type : (credit or upi) ");
		String type = sc.next();
		c.checkout(2000, PaymentFactory.getPayment(type));
		//Without factory class
		// c. checkout(3000 , new CreditCardStrategy());
	}

}
