package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.service.EmployeeDto;
import com.example.beautySalon.domain.dto.view.EmployeeViewDto;
import com.example.beautySalon.domain.dto.binding.AddEmployeeDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.entity.Employee;
import com.example.beautySalon.repositories.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepo employeeRepo;
    private ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, ModelMapper mapper) {
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    @Override
    public void addEmployee(AddEmployeeDto addEmployeeDto) {
        Employee employee=new Employee();
        employee.setAddress(addEmployeeDto.getAddress());
        employee.setAge(addEmployeeDto.getAge());
        employee.setFullName(addEmployeeDto.getFullName());
        employee.setStartOfWorkingDay(addEmployeeDto.getStartOfWorkingDay());
        employee.setEndOfWorkingDay(addEmployeeDto.getEndOfWorkingDay());
        employee.setIdentificationNumber(addEmployeeDto.getIdentificationNumber());
        employee.setSalary(addEmployeeDto.getSalary());
        employee.setEmail(addEmployeeDto.getEmail());
    this.employeeRepo.saveAndFlush(employee);
    }
    @Override
    public List<EmployeeViewDto> allEmployees() {
        return this.employeeRepo.findAll().stream().
                map(e->mapper.map(e, EmployeeViewDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) throws ObjectNotFoundException {
        if(getEmployeeById(id)==null){
            throw new ObjectNotFoundException(id,"Employee");
        }
        if(mapToEmployeeDto(getEmployeeById(id))==null){
            throw new ObjectNotFoundException(id,"Employee");
        }
        return mapToEmployeeDto(getEmployeeById(id));
    }
    private Employee getEmployeeById(Long id) {
        return this.employeeRepo.findEmployeeById(id);
    }

    private EmployeeDto mapToEmployeeDto(Employee employeeById1) {
        return mapper.map(employeeById1, EmployeeDto.class);
    }

    @Override
    public void deleteById(Long id) {
         this.employeeRepo.deleteById(id);
    }
    public Employee findEmployeeByEmail(String value) {
        return this.employeeRepo.findEmployeeByEmail(value).orElse(null);
    }
}
