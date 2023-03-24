package com.example.dealership1.web;

import com.example.dealership1.domain.dto.view.UserViewDto;
import com.example.dealership1.domain.entity.User;
import com.example.dealership1.services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ProfileControllerImpl {
    private UserServiceImpl userService;
    private ModelMapper mapper;

    public ProfileControllerImpl(UserServiceImpl userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/profile")
   @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView){
        UserViewDto userByUsername = mapper.map(this.userService.findUserByUsername(principal.getName()), UserViewDto.class);
        modelAndView.setViewName("/profile");
        modelAndView.addObject("user",userByUsername);
        return modelAndView;
    }
}
