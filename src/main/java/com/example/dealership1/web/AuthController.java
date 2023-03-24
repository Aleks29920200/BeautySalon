package com.example.dealership1.web;

import com.example.dealership1.domain.dto.binding.RegisterDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
public interface AuthController {
    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String register();

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registration(@Valid @ModelAttribute RegisterDto registerDto,
                               BindingResult result,
                               RedirectAttributes attr,
                               HttpServletRequest request,
                               HttpServletResponse response);
    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String login();
}
