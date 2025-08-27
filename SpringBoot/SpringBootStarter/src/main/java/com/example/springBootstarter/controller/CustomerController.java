package com.example.springBootstarter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootstarter.entity.Customer;


// rest controller is to add all the restful features -- rest best api (HttpMethods --- Get / Put /Post / Delete)
@RestController // used to create rest api's / endpoints
@RequestMapping("customer")
public class CustomerController {
	
	private List<Customer> customer = createList();
	
	
	@GetMapping("/add")
	public String getCustomers()
	{
		return "Customer Added into the database";
	}
	
	@PostMapping()
	public String addCustomer()
	{
		
		return "Customer added";
	}
	
	@GetMapping(value="/getDetails" , produces = "application/json")
	public List<Customer> viewAllcustomers()
	{
		return customer; // will display all the tempcustomers you added in a list
	}

	
	@DeleteMapping("/delete")
	public String deleteCustomer()
	{
		return "Customer Deleted";
		
	}
	
	@PutMapping("/update")
	public String updateCustomer()
	{
		return "Customer details are updated";
	}
	
	
	public static List<Customer> createList()
	{
		List<Customer> tempCustomers = new ArrayList<>();
		Customer c1 = new Customer(101,"Mani");
		Customer c2 = new Customer(102,"Manish");
		
		tempCustomers.add(c1);
		tempCustomers.add(c2);
		
		return tempCustomers;
		
	}

}
