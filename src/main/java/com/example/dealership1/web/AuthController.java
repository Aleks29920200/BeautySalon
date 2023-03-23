package com.example.dealership1.web;

import com.example.dealership1.domain.dto.binding.RegisterDto;
import com.example.dealership1.services.RoleService;
import com.example.dealership1.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class AuthController {
    private UserServiceImpl userService;
    private RoleService roleService;

@Autowired
    public AuthController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String register(){
        return "users/register";
    }
    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
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
    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String login(){
        return "users/login";
    }

    @ModelAttribute
    public RegisterDto registerDto() {
        return new RegisterDto();
    }
}
