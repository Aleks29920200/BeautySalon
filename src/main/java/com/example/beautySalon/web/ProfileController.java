package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.binding.EditUser;
import com.example.beautySalon.domain.dto.binding.RegisterDto;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
@Controller
public interface ProfileController {
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView);
    @PostMapping("/profile/edit")
    @PreAuthorize("isAuthenticated()")
    public String profileEdit(@Valid @ModelAttribute EditUser editUser,
                                    BindingResult result,
                                    RedirectAttributes attr,Principal principal) throws IOException;
}
