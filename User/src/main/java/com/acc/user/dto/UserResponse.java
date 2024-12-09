package com.acc.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    private int id;
    private String name;
    private String email;
    private String paymentMethod;
    private String srcAccount;
    private double availableAmount;
}
