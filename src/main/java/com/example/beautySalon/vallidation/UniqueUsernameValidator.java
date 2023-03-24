package com.example.beautySalon.vallidation;


import com.example.beautySalon.services.UserServiceImpl;
import com.example.beautySalon.vallidation.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserServiceImpl customerService;

    public UniqueUsernameValidator(UserServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.customerService.findUserByUsername(value)==null;
    }
}