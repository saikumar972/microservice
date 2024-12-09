package com.acc.user.customAnnotarion;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = PaymentType.class)
public @interface Payment {
    String message() default "The payment method you are entered is invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
