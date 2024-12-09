package com.acc.order.controller;

import com.acc.order.dto.OrderResponseDto;
import com.acc.order.entity.Order;
import com.acc.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService service;

    @PostMapping("/post")
    public String placeOrder(@RequestBody Order order) throws JsonProcessingException {
        return service.placeOrder(order);
    }

    @GetMapping("/get/{id}")
    public OrderResponseDto getOrderById(@PathVariable String id) throws JsonProcessingException {
        return service.getOrderDetails(id);
    }
}
