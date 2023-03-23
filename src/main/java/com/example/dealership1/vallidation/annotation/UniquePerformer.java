package com.example.dealership1.vallidation.annotation;

import com.example.dealership1.vallidation.UniquePerformerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniquePerformerValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePerformer {
    String message() default "Name already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
