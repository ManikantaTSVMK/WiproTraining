package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.beans.Order;
import com.example.repo.OrderRepo;

import jakarta.annotation.PostConstruct;

@Component
public class OrderDataLoader {

    @Autowired
    private OrderRepo repo;

    @PostConstruct
    public void loadDummyData() {
        // constructor: (orderId, amount, productName, payment)
        repo.save(new Order(1, 80000.0, "Laptop", null));
        repo.save(new Order(2, 50000.0, "Phone", null));
        repo.save(new Order(3, 30000.0, "Tablet", null));
        System.out.println("Order dummy data loaded into 'orders' table");
    }
}
