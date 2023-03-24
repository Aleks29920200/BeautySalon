package com.example.beautySalon.vallidation.annotation;

import com.example.beautySalon.vallidation.UniqueServiceNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = UniqueServiceNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueServiceName {
        String message() default "Name already exist";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
}
