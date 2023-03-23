package com.example.dealership1.vallidation;

import com.example.dealership1.services.UserServiceImpl;
import com.example.dealership1.vallidation.annotation.UniquePerformer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class UniquePerformerValidator implements ConstraintValidator<UniquePerformer, String> {

    private final UserServiceImpl service;

    public UniquePerformerValidator(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
