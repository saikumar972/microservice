package com.acc.payment.controller;

import com.acc.payment.entity.PaymentEntity;
import com.acc.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService service;
    @GetMapping("/get/{orderId}")
    public PaymentEntity getPaymentById(@PathVariable String orderId){
        return service.getPaymentResponse(orderId);
    }
}
