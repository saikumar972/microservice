package com.acc.user.customAnnotarion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PaymentType implements ConstraintValidator<Payment,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> paymentList= Arrays.asList("UPI","COD","DEBIT","CREDIT");
        if(paymentList.contains(value.toUpperCase())){
            return true;
        }
        return false;
    }
}
