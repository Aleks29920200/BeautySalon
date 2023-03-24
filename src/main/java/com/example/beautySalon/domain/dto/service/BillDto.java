package com.example.beautySalon.domain.dto.service;


import com.example.beautySalon.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BillDto extends BaseEntity {
    private Float amount;
    private Float price;
}
