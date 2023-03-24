package com.example.beautySalon.domain.dto.view;

import com.example.beautySalon.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceViewDto extends BaseEntity {
    private String name;
    private Float price;
    private String category;
    private String info;
    private FileViewDto img;
}
