package com.example.dealership1.vallidation.annotation;

import com.example.dealership1.vallidation.UniqueIdentificationNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = UniqueIdentificationNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueIdentificationNumber {
        String message() default "IdentificationNumber already exist";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
}
