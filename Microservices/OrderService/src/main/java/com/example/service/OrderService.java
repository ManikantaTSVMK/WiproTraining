package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Order;
import com.example.beans.Payment;
import com.example.repo.OrderRepo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    private final OrderRepo repo;
    private final PaymentFeignClient feignClient;

    @Autowired
    public OrderService(OrderRepo repo, PaymentFeignClient feignClient) {
        this.repo = repo;
        this.feignClient = feignClient;
    }

    public Order getOrderById(Integer orderId) {
        return repo.findById(orderId).map(order -> {
            try {
                Payment payment = feignClient.getPaymentByOrderId(orderId);
                order.setPayment(payment);
            } catch (Exception e) {
                System.err.println("Payment service not available: " + e.getMessage());
                order.setPayment(null);
            }
            return order;
        }).orElse(null);
    }

    // 🔹 Fixed version: Convert Iterable -> Stream
    public List<Order> getAllOrders() {
        return StreamSupport.stream(repo.findAll().spliterator(), false)
                .map(order -> {
                    try {
                        Payment payment = feignClient.getPaymentByOrderId(order.getOrderId());
                        order.setPayment(payment);
                    } catch (Exception e) {
                        System.err.println("Payment service not available for orderId "
                                + order.getOrderId() + ": " + e.getMessage());
                        order.setPayment(null);
                    }
                    return order;
                })
                .collect(Collectors.toList());
    }
}
