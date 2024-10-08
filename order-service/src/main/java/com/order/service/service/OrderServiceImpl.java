package com.order.service.service;

import com.order.service.entities.Order;
import com.order.service.exceptions.OrderNotFoundException;
import com.order.service.external.client.ProductService;
import com.order.service.model.OrderRequest;
import com.order.service.model.OrderResponse;
import com.order.service.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public long addOrder(OrderRequest orderRequest) {

        log.info("Placing order request {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        log.info("Stock availability check completed");

        Order order = Order.builder()
                        .amount(orderRequest.getTotalAmount())
                                .orderStatus("CREATED")
                                        .productId(orderRequest.getProductId())
                                                .orderDate(Instant.now())
                                                        .quantity(orderRequest.getQuantity())
                                                                .build();

        order = orderRepository.save(order);
        log.info("Order placed successfully with ID {}", order.getId());
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetailsById(long id) {
        return orderRepository
                .findById(id)
                .map(this::mapToOrderResponse)
                .orElseThrow(() -> new OrderNotFoundException("Order Not found", "ORDER_NOT_FOUND"));

    }

    private OrderResponse mapToOrderResponse(Order order) {
     //return  BeanUtils.copyProperties(order, OrderResponse.class);
        return  null;
    }

}
