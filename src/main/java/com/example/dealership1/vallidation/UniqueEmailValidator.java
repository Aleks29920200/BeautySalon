package com.example.dealership1.vallidation;


import com.example.dealership1.services.EmployeeServiceImpl;
import com.example.dealership1.services.UserServiceImpl;
import com.example.dealership1.vallidation.annotation.UniqueEmail;
import com.example.dealership1.vallidation.annotation.UniqueEmployeeEmail;
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
