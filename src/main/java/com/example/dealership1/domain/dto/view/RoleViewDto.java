package com.example.dealership1.domain.dto.view;

import com.example.dealership1.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleViewDto extends BaseEntity {
    String authorities;
}
