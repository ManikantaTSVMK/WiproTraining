package com.aopdemo.Aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
public class Demo {
	public void checkout(String message) {
		System.out.println("Demo Method");
	}

	@Override
	public String toString() {
		return "Demo [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
