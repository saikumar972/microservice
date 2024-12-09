package com.acc.payment.service;

import com.acc.payment.entity.PaymentEntity;
import com.acc.payment.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo repo;

    public PaymentEntity getPaymentResponse(String orderId){
        return repo.findByOrderId(orderId);
    }
}
