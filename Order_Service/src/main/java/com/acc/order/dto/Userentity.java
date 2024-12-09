package com.acc.order.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Userentity {
    private int id;
    private String name;
    private String email;
    private String paymentMethod;
    private String srcAccount;
    private double availableAmount;
}
