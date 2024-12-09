package com.acc.user.customAnnotarion;

import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface Userlog {
    String message() default "no";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
