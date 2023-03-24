package com.example.beautySalon.domain.dto.service;

import com.example.beautySalon.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceDto extends BaseEntity {
    private String name;
    private float price;
    private String category;
    private String info;
    private FileDto img;
}
