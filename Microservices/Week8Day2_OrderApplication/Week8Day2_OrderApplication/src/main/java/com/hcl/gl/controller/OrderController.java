package com.hcl.gl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcl.gl.dto.TransactionRequest;
import com.hcl.gl.dto.TransactionResponse;
import com.hcl.gl.service.OrderService;

@RestController  
@RequestMapping("/order")   //prefix for all the api's
public class OrderController 
{
    @Autowired
    private OrderService service;
	
	//http://localhost:8081/order/addOrder
    @PostMapping("/addOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
        return service.saveOrder(request);
    }
}























