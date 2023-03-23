package com.example.dealership1.vallidation;

import com.example.dealership1.services.ServiceImpl;
import com.example.dealership1.vallidation.annotation.UniqueServiceName;
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
