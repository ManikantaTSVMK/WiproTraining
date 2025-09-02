package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.beans.Payment;
import com.example.service.PaymentService;
import com.example.repo.PaymentRepo;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService service;

    @Autowired
    PaymentRepo repo;

    @GetMapping("/order/{orderId}")
    public Payment getPaymentByOrderId(@PathVariable("orderId") Integer orderId) {
        return service.getPaymentByOrderId(orderId);
    }

    @GetMapping("/all")
    public Iterable<Payment> getAllPayments() {
        return repo.findAll();
    }
}
