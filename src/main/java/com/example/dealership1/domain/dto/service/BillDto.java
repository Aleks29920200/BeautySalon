package com.example.dealership1.domain.dto.service;


import com.example.dealership1.domain.entity.BaseEntity;
import jakarta.validation.constraints.Positive;
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
