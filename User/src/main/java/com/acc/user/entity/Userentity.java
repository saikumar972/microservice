package com.acc.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="user")
public class Userentity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String paymentMethod;
    private String srcAccount;
    private double availableAmount;
}
