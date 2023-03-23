package com.example.dealership1.services;

import com.example.dealership1.domain.dto.binding.AddServiceDto;
import com.example.dealership1.domain.dto.error.ObjectNotFoundException;
import com.example.dealership1.domain.dto.view.ServiceViewDto;

import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Service
public interface Service{
    com.example.dealership1.domain.entity.Service findServiceByName(String name);
    void saveService(AddServiceDto serviceDto) throws IOException;
ServiceViewDto findServiceById(Long id) throws ObjectNotFoundException;
    void deleteById(Long id);

    List<ServiceViewDto> allServices();
}
