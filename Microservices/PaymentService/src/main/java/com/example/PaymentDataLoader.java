package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.beans.Payment;
import com.example.repo.PaymentRepo;

import jakarta.annotation.PostConstruct;

@Component
public class PaymentDataLoader {

    @Autowired
    private PaymentRepo repo;

    @PostConstruct
    public void loadDummyData() {
        // constructor: (paymentId, orderId, status, transactionId)
        repo.save(new Payment(101, 1, "SUCCESS", "TXN1001"));
        repo.save(new Payment(102, 2, "PENDING", "TXN1002"));
        repo.save(new Payment(103, 3, "FAILED", "TXN1003"));
        System.out.println("✅ Payment dummy data loaded into 'payments' table");
    }
}
