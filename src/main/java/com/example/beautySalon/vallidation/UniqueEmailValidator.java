package com.example.beautySalon.vallidation;


import com.example.beautySalon.services.UserServiceImpl;
import com.example.beautySalon.vallidation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserServiceImpl customerService;

    public UniqueEmailValidator(UserServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.customerService.findUserByEmail(value) == null;
    }
}
