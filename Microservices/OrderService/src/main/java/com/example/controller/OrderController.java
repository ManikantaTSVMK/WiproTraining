package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.beans.Order;
import com.example.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("{orderId}")
    public Order getOrderById(@PathVariable("orderId") Integer orderId) {
        return service.getOrderById(orderId);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return service.getAllOrders();  // ✅ Now returns orders with payment details
    }
}
