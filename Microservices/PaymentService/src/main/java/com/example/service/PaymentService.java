package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.beans.Payment;
import com.example.repo.PaymentRepo;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo repo;

    public Payment getPaymentByOrderId(Integer orderId) {
        return repo.findByOrderId(orderId);
    }
}
