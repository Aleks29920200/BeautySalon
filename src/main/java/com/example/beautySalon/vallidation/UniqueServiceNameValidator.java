package com.example.beautySalon.vallidation;

import com.example.beautySalon.services.ServiceImpl;
import com.example.beautySalon.vallidation.annotation.UniqueServiceName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueServiceNameValidator implements ConstraintValidator<UniqueServiceName,String> {
    private ServiceImpl service;

    public UniqueServiceNameValidator(ServiceImpl service) {
        this.service = service;
    }

    @Override
    public void initialize(UniqueServiceName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.service.findServiceByName(s)==null;
    }
}
