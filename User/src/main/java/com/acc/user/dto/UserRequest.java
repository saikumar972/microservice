package com.acc.user.dto;

import com.acc.user.customAnnotarion.Payment;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequest {
    @NotBlank(message="name is a mandatory field to mention")
    private String name;
    @Email(message="Given email format is incorrect")
    private String email;
    @Payment(message="The mentioned payment method is invalid")
    private String paymentMethod;
    @NotBlank
    private String srcAccount;
    @Min(value = 1000, message = "The entered amount must be at least 1000")
    @Max(value = 100000, message = "The entered amount must not exceed 100000")
    private double availableAmount;
}
