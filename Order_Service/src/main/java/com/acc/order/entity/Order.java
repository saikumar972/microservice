package com.acc.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    private String orderId;
    private String name;
    private String category;
    private double price;
    private Date purchaseDate;
    private int userId;
    private String paymentMode;
}
