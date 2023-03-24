package com.example.dealership1.web;


import com.example.dealership1.services.EmployeeService;
import com.example.dealership1.services.UserServiceImpl;
import org.modelmapper.ModelMapper;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class HomeControllerImpl implements HomeController{
    private UserServiceImpl userService;

    private ModelMapper mapper;
    public HomeControllerImpl(UserServiceImpl userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public String index() {
        return "index";
    }
    @Override
    public String indexLanguageToEnglish() {
        return "index";
    }
    @Override
    public String home(Principal principal,ModelAndView modelAndView){
        modelAndView.addObject("username",principal.getName());
        return "home";
    }
    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public String getLogout(){
        return "redirect:/";
    }
}
