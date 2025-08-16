package com.company.beans;

import org.springframework.stereotype.Component;


// to tell to the container that this is the bean component class (java class)
@Component("CCProcessor")
public class CreditCardProcessor implements PaymentProcessor {

	@Override
	public void pay(double amount) {
		
		System.out.println(" payment of Rs :" + amount + " via Credit Card is done");
		
	}

}