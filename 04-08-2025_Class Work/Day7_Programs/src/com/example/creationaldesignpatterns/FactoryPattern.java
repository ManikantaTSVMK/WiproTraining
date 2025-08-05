package com.example.creationaldesignpatterns;


// meant for creating different types of payment methods(credit , upi)based on user's input

interface Payment
{
	
void pay(double amount);
}


class CreditCard implements Payment
{

	@Override
	public void pay(double amount) {
		System.out.println("Payment done using credit card :");
		
	}
	
}

class Upi implements Payment
{

	@Override
	public void pay(double amount) {
		System.out.println("Payment done using Upi :");
		
		
	}
	
}

// Factory class
class PaymentFactory{
	
	public static Payment getPayment(String type)
	{
		
		return switch(type)
				{
					case "credit" -> new CreditCard();
					case "upi" -> new Upi();
					default -> throw new IllegalArgumentException("Unexpected value: " + type);
				};
	}
	
	
	
}




public class FactoryPattern {

	public static void main(String[] args) {
		Payment p1 = PaymentFactory.getPayment("credit");
		p1.pay(12000);
		
		Payment p2 = PaymentFactory.getPayment("upi");
		p2.pay(12000);
	}

}