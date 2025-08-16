package com.company.beans;

import org.springframework.stereotype.Component;

@Component("UpiProcess")
public class UpiProcessor implements PaymentProcessor {

	@Override
	public void pay(double amount) {
		
		System.out.println(" payment of Rs :" + amount + " via Upi is done");
		
	}

}