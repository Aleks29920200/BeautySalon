package com.example.beautySalon.domain.dto.view;

import com.example.beautySalon.domain.entity.BaseEntity;

import java.time.LocalTime;

public class EmployeeViewDto extends BaseEntity {
    private String fullName;
    private int age;
    private String address;
    private LocalTime startOfWorkingDay;
    private LocalTime endOfWorkingDay;
    private Double salary;
    private String identificationNumber;
    private String email;

    public EmployeeViewDto() {
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
