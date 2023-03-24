package com.example.beautySalon.vallidation;

import com.example.beautySalon.services.UserServiceImpl;
import com.example.beautySalon.vallidation.annotation.UniquePerformer;
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
