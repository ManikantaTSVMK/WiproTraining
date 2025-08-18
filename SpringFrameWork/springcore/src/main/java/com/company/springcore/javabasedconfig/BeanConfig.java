package com.company.springcore.javabasedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	//It' returning address class object
	@Bean("addr")
	public Address getAddress()
	{
		return new Address(6465,"mg road" , "Delhi");
	}
	
	@Bean("emp")
		public Employee getEmployee()
		{
			return new Employee();
		}
	
	

}