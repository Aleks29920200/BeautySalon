package com.example.beautySalon.web;

import com.example.beautySalon.services.UserServiceImpl;
import com.example.beautySalon.domain.dto.binding.RegisterDto;
import com.example.beautySalon.services.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthControllerImpl implements AuthController {
    private UserServiceImpl userService;
    private RoleService roleService;

@Autowired
    public AuthControllerImpl(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @Override
    public String register(){
        return "users/register";
    }
    @Override
    public String registration(@Valid @ModelAttribute RegisterDto registerDto,
                               BindingResult result,
                               RedirectAttributes attr,
                               HttpServletRequest request,
                               HttpServletResponse response){

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            attr
                    .addFlashAttribute("registerDto", registerDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDto", result);

            return "redirect:/users/register";
        }
        this.userService.registerUser(registerDto);
        return "redirect:/users/login";
    }
    @Override
    public String login(){
        return "users/login";
    }

    @ModelAttribute
    public RegisterDto registerDto() {
        return new RegisterDto();
    }
}
