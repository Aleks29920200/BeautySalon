package com.example.dealership1.domain.dto.binding;


import com.example.dealership1.vallidation.annotation.UniqueEmployeeEmail;
import com.example.dealership1.vallidation.annotation.UniqueIdentificationNumber;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public class AddEmployeeDto {
    @Size(min=10,message = "FullName length must be at least 10 symbols!")
    @NotBlank
    private String fullName;
    @Positive(message = "Age must be positive number!")
    private int age;
    @Size(min=5,max=30,message = "Address length must be between 5 and 30 characters!")
    @NotBlank
    private String address;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @PastOrPresent
    private LocalTime startOfWorkingDay;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @FutureOrPresent
    private LocalTime endOfWorkingDay;
    @Positive(message = "Salary must be positive number!")
    private Double salary;
    @NotBlank
    @Size(min = 8)
    @UniqueIdentificationNumber
    private String identificationNumber;
    @Email
    @UniqueEmployeeEmail
    private String email;
    public AddEmployeeDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public LocalTime getStartOfWorkingDay() {
        return startOfWorkingDay;
    }

    public void setStartOfWorkingDay(LocalTime startOfWorkingDay) {
        this.startOfWorkingDay = startOfWorkingDay;
    }

    public LocalTime getEndOfWorkingDay() {
        return endOfWorkingDay;
    }

    public void setEndOfWorkingDay(LocalTime endOfWorkingDay) {
        this.endOfWorkingDay = endOfWorkingDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
