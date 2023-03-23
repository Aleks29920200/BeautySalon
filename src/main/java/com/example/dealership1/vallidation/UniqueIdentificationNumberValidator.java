package com.example.dealership1.vallidation;

import com.example.dealership1.repositories.EmployeeRepo;
import com.example.dealership1.vallidation.annotation.UniqueIdentificationNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class UniqueIdentificationNumberValidator implements ConstraintValidator<UniqueIdentificationNumber,String> {
    private EmployeeRepo employeeRepo;

    public UniqueIdentificationNumberValidator(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    @Override
    public void initialize(UniqueIdentificationNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.employeeRepo.findEmployeeByIdentificationNumber(s)==null;
    }
}
