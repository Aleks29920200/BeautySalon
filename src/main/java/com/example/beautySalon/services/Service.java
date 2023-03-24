package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.AddServiceDto;
import com.example.beautySalon.domain.dto.view.ServiceViewDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;

import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Service
public interface Service{
    com.example.beautySalon.domain.entity.Service findServiceByName(String name);
    void saveService(AddServiceDto serviceDto) throws IOException;
ServiceViewDto findServiceById(Long id) throws ObjectNotFoundException;
    void deleteById(Long id);

    List<ServiceViewDto> allServices();
}
