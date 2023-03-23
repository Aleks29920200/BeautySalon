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
public class HomeController{
    private UserServiceImpl userService;

    private ModelMapper mapper;
    public HomeController(UserServiceImpl userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/index")
   @PreAuthorize(value = "isAnonymous()")
    public String index() {
        return "index";
    }
    @PostMapping("/index?language")
    @PreAuthorize(value = "isAnonymous()")
    public String indexLanguageToEnglish() {
        return "index";
    }
    @GetMapping("/home")
   @PreAuthorize(value = "isAuthenticated()")
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
