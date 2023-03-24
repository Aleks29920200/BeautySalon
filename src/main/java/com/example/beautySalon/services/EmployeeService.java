package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.AddEmployeeDto;
import com.example.beautySalon.domain.dto.service.EmployeeDto;
import com.example.beautySalon.domain.dto.view.EmployeeViewDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    void addEmployee(AddEmployeeDto addEmployeeDto);

    List<EmployeeViewDto> allEmployees();
    EmployeeDto findEmployeeById(Long id) throws ObjectNotFoundException;
    void deleteById(Long id);


}
