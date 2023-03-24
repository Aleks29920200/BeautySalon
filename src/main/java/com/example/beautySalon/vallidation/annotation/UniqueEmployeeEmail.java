package com.example.beautySalon.vallidation.annotation;

import com.example.beautySalon.vallidation.UniqueEmployeeEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmployeeEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmployeeEmail {
    String message() default "Email of employee already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
