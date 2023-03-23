package com.example.dealership1.schedulingJobs;

import com.example.dealership1.domain.entity.Employee;
import com.example.dealership1.services.EmailService;
import com.example.dealership1.services.EmployeeService;
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

//    @Async
//    @Scheduled(cron = "0 0/1 * 1/1 * ?")
//    public void emailScheduledJob() {
//        List<User> users = userService.findAllUsers()
//                .stream()
//                .map(userServiceModel -> this.modelMapper.map(userServiceModel, User.class))
//                .collect(Collectors.toList());
//
//        users.forEach(user -> emailService.sendSimpleMessage(
//                user.getEmail(), EmailConstants.THANK_YOU_MESSAGE_TITLE, EmailConstants.THANK_YOU_MESSAGE_TEXT));
//    }
// Testing the emailing service for demo ---- the next one is greetings email for christmas and new year.

   // @Scheduled(cron = "* * 15 22 3 ?")
    public void christmasScheduleJob() {
       List<Employee> employees = this.employeeService.allEmployees();
        employees.forEach(employee -> emailService.sendEmail(
                employee.getEmail(), EmailConstants.CHRISTMAS_MESSAGE_TITLE, EmailConstants.CHRISTMAS_MESSAGE_TEXT));
    }
}
