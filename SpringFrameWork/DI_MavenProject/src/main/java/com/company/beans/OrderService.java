package com.company.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//To a container I need to tell that this is my component class 

@Component
public class OrderService {
	
	private PaymentProcessor paymentProcessor;
	
	// autowired is the annotation used for injecting the child class object
	@Autowired
	public OrderService(@Qualifier("CCProcessor") PaymentProcessor paymentProcessor)
	{
		this.paymentProcessor = paymentProcessor;
		
	}
	
	public void placeOrder(double actualAmount)
	{
		
		paymentProcessor.pay(actualAmount);
		System.out.println("Order placed successfully");
	}
}