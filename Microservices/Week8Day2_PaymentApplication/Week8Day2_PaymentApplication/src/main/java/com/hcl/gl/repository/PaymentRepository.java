package com.hcl.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.gl.pojo.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>
{
	Payment findByOrderId(int orderId);

}
