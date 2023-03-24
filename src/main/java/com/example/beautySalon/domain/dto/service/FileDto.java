package com.example.beautySalon.domain.dto.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileDto {
    private int id;
    private String fileName;
    private byte[] fileData;
    private String contentType;
}
