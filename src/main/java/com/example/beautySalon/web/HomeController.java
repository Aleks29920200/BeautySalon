package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
@Controller
public interface HomeController{
    @GetMapping("/index")
    @PreAuthorize(value = "isAnonymous()")
    public String index(Principal principal);
    @GetMapping("/about")
    @PreAuthorize(value = "isAnonymous()")
    public String about();
    @PostMapping("/index?language")
    @PreAuthorize(value = "isAnonymous()")
    public String indexLanguageToEnglish();
    @GetMapping("/home")
    @PreAuthorize(value = "isAuthenticated()")
    public String home(Principal principal, ModelAndView modelAndView) throws ObjectNotFoundException;
    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public String getLogout();
}
