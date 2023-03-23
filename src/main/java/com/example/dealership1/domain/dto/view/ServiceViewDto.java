package com.example.dealership1.domain.dto.view;

import com.example.dealership1.domain.entity.BaseEntity;
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
