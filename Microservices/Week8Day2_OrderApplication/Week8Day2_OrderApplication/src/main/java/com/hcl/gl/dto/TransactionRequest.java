package com.hcl.gl.dto;


import com.hcl.gl.pojo.Order;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private Order order;
    private Payment payment;
}
