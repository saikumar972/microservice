package com.acc.payment.configuration;

import com.acc.payment.dto.Order;
import com.acc.payment.dto.UserResponse;
import com.acc.payment.entity.PaymentEntity;
import com.acc.payment.repo.PaymentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
@Slf4j
public class ProducerConfig {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentRepo repo;

//    private static final String BASE_URL="http://localhost:9001/user/";

    private static final String BASE_URL="http://USER-SERVICE/user/";

    @KafkaListener(topics = "ordertopic", groupId = "ordergroup")
    public void listenOrdermessages(String orderData) throws Exception {
        Order order = new ObjectMapper().readValue(orderData, Order.class);
        log.info("ProducerConfig :: checking order data {}",new ObjectMapper().writeValueAsString(order));
        PaymentEntity payment = PaymentEntity.builder()
                .paymentMode(order.getPaymentMode())
                .amount(order.getPrice())
                .paidDate(new Date())
                .userId(order.getUserId())
                .orderId(order.getOrderId())
                .build();
        log.info("ProducerConfig :: checking payment data {}",new ObjectMapper().writeValueAsString(payment));
        if (payment.getPaymentMode().equalsIgnoreCase("COD")) {
            payment.setPaymentStatus("pending");
            PaymentEntity paymentEntity=repo.save(payment);
            System.out.println("payment was in in pending state and payment state is "+paymentEntity.toString());
        } else {
            UserResponse user = restTemplate.getForObject(BASE_URL + "get"+"/"+ payment.getUserId(), UserResponse.class);
            log.info("ProducerConfig :: user by id data {}",new ObjectMapper().writeValueAsString(user));
            if (user.getAvailableAmount() < payment.getAmount()) {
                throw new RuntimeException("Insufficient balance in user account");
            } else {
                payment.setPaymentStatus("paid");
                restTemplate.put(BASE_URL + "update" + "/" + payment.getUserId() + "/" + payment.getAmount(), null);
            }
            repo.save(payment);
        }
        System.out.println("messages received from order queue " + new ObjectMapper().writeValueAsString(order));
    }
}
