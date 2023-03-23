package com.example.dealership1.services;

import com.example.dealership1.domain.dto.binding.AddEmployeeDto;
import com.example.dealership1.domain.dto.error.ObjectNotFoundException;
import com.example.dealership1.domain.dto.service.EmployeeDto;
import com.example.dealership1.domain.dto.view.EmployeeViewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    void addEmployee(AddEmployeeDto addEmployeeDto);

    List<EmployeeViewDto> allEmployees();
    EmployeeDto findEmployeeById(Long id) throws ObjectNotFoundException;
    void deleteById(Long id);


}
