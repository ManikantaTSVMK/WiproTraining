package com.aopdemo.Aop;

import java.beans.beancontext.BeanContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(beanConfig.class);
    	ShoppingCart cart = context.getBean(ShoppingCart.class);
    	String result = cart.checkout("Laptop");
    	System.out.println(result);
    }
}
