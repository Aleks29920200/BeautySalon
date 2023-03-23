package com.example.dealership1.vallidation;


import com.example.dealership1.services.UserServiceImpl;
import com.example.dealership1.vallidation.annotation.UniqueUsername;
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