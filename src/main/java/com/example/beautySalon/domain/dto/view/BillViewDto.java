package com.example.beautySalon.domain.dto.view;


import com.example.beautySalon.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class BillViewDto extends BaseEntity {
    private BigDecimal amount;
    private BigDecimal price;
}

