package com.aopdemo.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
//	@Before("execution(* com.aopdemo.Aop.ShoppingCart.checkout(..))")
//	public void beforeLogger(JoinPoint jb) {
//		System.out.println();
//		System.out.println("Before Loggers");
//	}
//	
//	@Around("execution(* com.aopdemo.Aop.*.checkout(..))")
//	public void aroundLogger(JoinPoint jb) {
//		String arg = jb.getArgs()[0].toString();
//		System.out.println(arg);
//	}
	
	@AfterReturning(pointcut="execution(* *..*.checkout(..))", returning = "returnString")
	public void getNameReturningAdvice(String returnString) {
		System.out.println("getNameReturningAdvice executed Returned string = "+returnString);
	}
	
//	@AfterThrowing("within (com.aopdemo.Aop.ShoppingCart)")
//    public void logAfterThrowing() {
//        System.out.println("Exception thrown");
//    }
//	
//	@After("execution(* *..*.checkout(..))")
//	public void afterLogger(JoinPoint jb) 
//	{
//		String arg = jb.getArgs()[0].toString();
//		System.out.println("After checkout is working " + arg + " done.");
//	}
}


