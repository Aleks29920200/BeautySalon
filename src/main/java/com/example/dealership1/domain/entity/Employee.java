package com.example.dealership1.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseEntity{
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private LocalTime startOfWorkingDay;
    @Column(nullable = false)
    private LocalTime endOfWorkingDay;
    @Column(nullable = false)
    private Double salary;
    @Column(nullable = false,unique = true)
    private String identificationNumber;
    @Column(nullable = false,unique = true)
    private String email;

}
