package com.example.beautySalon.domain.dto.service;

import com.example.beautySalon.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto extends BaseEntity {
    String authorities;
}
