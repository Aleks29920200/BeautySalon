package com.example.dealership1.domain.dto.service;

import com.example.dealership1.domain.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto extends BaseEntity {
    String authorities;
}
