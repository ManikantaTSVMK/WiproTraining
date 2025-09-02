package com.example.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Integer paymentId;
    private Integer orderId;
    private String status;
    private String transactionId;
}
