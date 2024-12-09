package com.acc.order.service;

import com.acc.order.dto.OrderResponseDto;
import com.acc.order.dto.PaymentEntity;
import com.acc.order.dto.Userentity;
import com.acc.order.entity.Order;
import com.acc.order.repo.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jdk.jfr.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderRepository repo;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${order.producer.topic.name}")
    private String topicName;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    private static final String ORDER_SERVICE="orderservice";



    //    private final static String PAYMENT_URL = "http://localhost:9003/payment/";
//    private final static String USER_URL = "http://localhost:9001/user/";
    private final static String USER_URL = "http://USER-SERVICE/user/";
    private final static String PAYMENT_URL = "http://PAYMENT-SERVICE/payment/";


    public String placeOrder(Order order) throws JsonProcessingException {
        order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        order.setPurchaseDate(new Date());
        Order modifiedOrder = repo.save(order);
        kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(modifiedOrder));
        return "messages sent to kafka queue " + new ObjectMapper().writeValueAsString(modifiedOrder);
    }
    @CircuitBreaker(name = ORDER_SERVICE,fallbackMethod = "getOrder")
    public OrderResponseDto getOrderDetails(String orderId) throws JsonProcessingException {
        System.out.println("in order service method for fetching order details");
        PaymentEntity paymentEntity = restTemplate.getForObject(PAYMENT_URL + "get" + "/" + orderId, PaymentEntity.class);
        System.out.println("checking payment data " + new ObjectMapper().writeValueAsString(paymentEntity));
        Userentity userentity = restTemplate.getForObject(USER_URL + "get" + "/" + paymentEntity.getUserId(), Userentity.class);
        System.out.println("checking user data " + new ObjectMapper().writeValueAsString(userentity));
        return OrderResponseDto.builder()
                .paymentEntity(paymentEntity)
                .userentity(userentity)
                .build();
    }

    public OrderResponseDto getOrder(Exception ex){
        return OrderResponseDto.builder().ErrorInfo("Present payment/user service is down please try again later").build();
    }
}
