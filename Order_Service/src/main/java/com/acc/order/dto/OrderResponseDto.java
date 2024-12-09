package com.acc.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponseDto {
    private String ErrorInfo;
    private PaymentEntity paymentEntity;
    private Userentity userentity;
}
