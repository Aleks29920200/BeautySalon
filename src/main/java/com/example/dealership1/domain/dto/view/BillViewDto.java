package com.example.dealership1.domain.dto.view;


import com.example.dealership1.domain.entity.BaseEntity;
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
