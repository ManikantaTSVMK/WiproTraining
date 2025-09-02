package com.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.beans.Payment;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, Integer> {
    Payment findByOrderId(Integer orderId);
}
