package com.order.service.service;

import com.order.service.model.OrderRequest;

public interface OrderService {
    long addOrder(OrderRequest orderRequest);
    Object getOrderDetailsById(long id);
}
