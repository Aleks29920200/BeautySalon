package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.binding.EditUser;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
@Controller
public interface ProfileController {
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView);
    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profileEdit(Principal principal,ModelAndView modelAndView, EditUser editUser);

}
