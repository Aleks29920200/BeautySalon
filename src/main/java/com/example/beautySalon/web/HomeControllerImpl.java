package com.example.beautySalon.web;


import com.example.beautySalon.services.ServiceImpl;
import com.example.beautySalon.services.UserServiceImpl;
import org.modelmapper.ModelMapper;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class HomeControllerImpl implements HomeController{
    private UserServiceImpl userService;

    private ModelMapper mapper;
    private ServiceImpl service;
    public HomeControllerImpl(UserServiceImpl userService, ModelMapper mapper, ServiceImpl service) {
        this.userService = userService;
        this.mapper = mapper;
        this.service = service;
    }

    @Override
    public String index(Principal principal) {
        if(principal!=null){
            return "home";
        }
        return "index";
    }

    @Override
    public String about() {
        return "users/about";
    }

    @Override
    public String indexLanguageToEnglish() {
        return "index";
    }
    @Override
    public String home(Principal principal,ModelAndView modelAndView){
        modelAndView.addObject("username",principal.getName());
        modelAndView.addObject("services",this.service.allServices());
        return "home";
    }
    @Override
    public String getLogout(){
        return "redirect:/";
    }
}
