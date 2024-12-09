package com.acc.payment.repo;

import com.acc.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentEntity, Integer> {
    PaymentEntity findByOrderId(String orderId);
}
