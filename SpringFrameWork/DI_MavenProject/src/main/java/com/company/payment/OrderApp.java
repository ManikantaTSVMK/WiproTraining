package com.company.payment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.company.beans.OrderService;
import com.company.config.AppConfig;

import org.springframework.context.ApplicationContext;

public class OrderApp {

	public static void main(String[] args) {
		
		// call the container -- ApplicationContext
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	   // From the object of container you are asking an object of OrderService class
		OrderService  orderService = context.getBean(OrderService.class);
		
		orderService.placeOrder(1000);
		
	}

}