package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.binding.EditUser;
import com.example.beautySalon.domain.dto.view.UserViewDto;
import com.example.beautySalon.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ProfileControllerImpl implements ProfileController{
    private UserServiceImpl userService;
    private ModelMapper mapper;

    public ProfileControllerImpl(UserServiceImpl userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public ModelAndView profile(Principal principal, ModelAndView modelAndView){
        UserViewDto userByUsername = mapper.map(this.userService.findUserByUsername(principal.getName()), UserViewDto.class);
        modelAndView.setViewName("/profile");
        modelAndView.addObject("user",userByUsername);
        return modelAndView;
    }
    @Override
    public ModelAndView profileEdit(Principal principal,ModelAndView modelAndView,EditUser editUser) {
        modelAndView.setViewName("/profile");
        modelAndView.addObject("edit",editUser);
        this.userService.editUser(editUser,principal.getName());
        return modelAndView;
    }
}
