package com.example.beautySalon.web;


import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.dto.service.ServiceDto;
import com.example.beautySalon.domain.dto.view.ServiceViewDto;
import com.example.beautySalon.domain.dto.view.UserViewDto;
import com.example.beautySalon.domain.entity.Service;
import com.example.beautySalon.services.EmployeeServiceImpl;
import com.example.beautySalon.services.ServiceImpl;
import com.example.beautySalon.services.UserServiceImpl;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;


@Controller
public class HomeControllerImpl implements HomeController{
    private UserServiceImpl userService;

    private ModelMapper mapper;
    private ServiceImpl service;
    private EmployeeServiceImpl employeeService;
    public HomeControllerImpl(UserServiceImpl userService, ModelMapper mapper, ServiceImpl service, EmployeeServiceImpl employeeService) {
        this.userService = userService;
        this.mapper = mapper;
        this.service = service;
        this.employeeService = employeeService;
    }

    @Override
    public String index(Principal principal) {
        if(principal!=null){
            return "home";
        }
        return "index";
    }

    @Override
    public String about(ModelAndView modelAndView) {
        modelAndView.setViewName("users/about");
        modelAndView.addObject("employees",this.employeeService.allEmployees());
        return "users/about";
    }

    @Override
    public String indexLanguageToEnglish() {
        return "index";
    }
    @Override
    public String home(){
        return "home";
    }


    @Override
    public String getLogout(){
        return "redirect:/";
    }

}
