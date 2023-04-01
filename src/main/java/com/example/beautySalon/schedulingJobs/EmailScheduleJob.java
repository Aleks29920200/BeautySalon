package com.example.beautySalon.schedulingJobs;

import com.example.beautySalon.domain.dto.view.EmployeeViewDto;
import com.example.beautySalon.services.EmailService;
import com.example.beautySalon.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailScheduleJob {

    private final EmployeeService employeeService;
    private final EmailService emailService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmailScheduleJob(EmployeeService employeeService,EmailService emailService, ModelMapper modelMapper) {
        this.emailService = emailService;
        this.modelMapper = modelMapper;
        this.employeeService=employeeService;
    }
   @Scheduled(cron = "0 1 0 25 12 ?")
    public void christmasScheduleJob() {
       List<EmployeeViewDto> employees = this.employeeService.allEmployees();
        employees.forEach(employee -> emailService.sendSimpleMessage(
                employee.getEmail(), EmailConstants.CHRISTMAS_MESSAGE_TITLE, EmailConstants.CHRISTMAS_MESSAGE_TEXT));
    }
}
