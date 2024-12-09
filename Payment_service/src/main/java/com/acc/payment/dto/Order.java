package com.acc.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private int id;
    private String orderId;
    private String name;
    private String category;
    private double price;
    private Date purchaseDate;
    private int userId;
    private String paymentMode;
}
