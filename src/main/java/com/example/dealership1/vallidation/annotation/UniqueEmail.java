package com.example.dealership1.vallidation.annotation;

import com.example.dealership1.vallidation.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email of user already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

