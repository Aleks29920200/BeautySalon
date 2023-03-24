package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.binding.AddEmployeeDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public interface EmployeeController {
    @GetMapping("add-employee")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public String addService();
    @PostMapping("add-employee")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public String service(@Valid @ModelAttribute("addEmployeeDto") AddEmployeeDto addEmployeeDto,
                          BindingResult bindingResult,
                          RedirectAttributes attr);
    @GetMapping("all-employees")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public String allServices(Model model);
    @GetMapping("all-employees/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException;
    @PostMapping("all-employees/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable Long id);
}
