package com.order.service.controller;

import com.order.service.model.OrderRequest;
import com.order.service.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> addOrder(@RequestBody OrderRequest orderRequest) {
        long orderId = orderService.addOrder(orderRequest);
        log.info("Place order with ID: {} ", orderId);

        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

   /* @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("id") long id){

        orderService.getOrderDetailsById(id);
    }*/
}
