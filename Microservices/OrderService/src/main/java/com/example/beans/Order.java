package com.example.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")  // 👈 FIX: avoid reserved keyword
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private Integer orderId;
    private Double amount;
    private String productName;

    @Transient   // not persisted, only populated from Payment service
    private Payment payment;
}
