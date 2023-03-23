package com.example.dealership1.vallidation;

import com.example.dealership1.services.EmployeeServiceImpl;
import com.example.dealership1.vallidation.annotation.UniqueEmployeeEmail;
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
