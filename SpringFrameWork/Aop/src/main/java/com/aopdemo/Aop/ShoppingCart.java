package com.aopdemo.Aop;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
	public String checkout(String cart) {
		System.out.println(cart);
		System.out.println("Checkout method from shoppingCart class");
		return "My Order is :- "+ cart;
	}
}
