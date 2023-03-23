package com.example.dealership1.vallidation.annotation;

import com.example.dealership1.vallidation.UniqueServiceNameValidator;
import com.example.dealership1.vallidation.UniqueUsernameValidator;
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
