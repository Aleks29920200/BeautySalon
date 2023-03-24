package com.example.beautySalon.domain.dto.view;

import com.example.beautySalon.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleViewDto extends BaseEntity {
    String authorities;
}
