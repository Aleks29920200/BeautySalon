package com.example.dealership1.domain.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileViewDto {
    private int id;
    private String fileName;
    private byte[] fileData;
    private String contentType;
}
