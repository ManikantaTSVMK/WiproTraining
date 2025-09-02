package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.beans.Payment;

// Feign client to call Payment Microservice
@FeignClient(
    name = "payment-service",           // logical name
    url = "http://localhost:8083"       // Payment service base URL
)
public interface PaymentFeignClient {

    // Fetch payment details by orderId
    @GetMapping("/payments/order/{orderId}")
    Payment getPaymentByOrderId(@PathVariable("orderId") Integer orderId);
}
