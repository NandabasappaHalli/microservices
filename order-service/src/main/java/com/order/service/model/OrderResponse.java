package com.order.service.model;

import lombok.Data;

import java.time.Instant;

@Data
public class OrderResponse {
    private long orderId;
    private long productId;
    private long quantity;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
}
