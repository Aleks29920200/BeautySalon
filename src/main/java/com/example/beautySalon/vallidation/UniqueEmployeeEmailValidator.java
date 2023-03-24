package com.example.beautySalon.vallidation;

import com.example.beautySalon.services.EmployeeServiceImpl;
import com.example.beautySalon.vallidation.annotation.UniqueEmployeeEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmployeeEmailValidator implements ConstraintValidator<UniqueEmployeeEmail, String> {

    private final EmployeeServiceImpl employeeService;

    public UniqueEmployeeEmailValidator(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.employeeService.findEmployeeByEmail(value) == null;
    }
}
