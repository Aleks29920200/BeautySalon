package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.binding.AddEmployeeDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boss")
public interface EmployeeController {
    @GetMapping("/add-employee")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public String addEmployee();
    @PostMapping("/add-employee")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public String employeePost(@Valid @ModelAttribute("addEmployeeDto") AddEmployeeDto addEmployeeDto,
                          BindingResult bindingResult,
                          RedirectAttributes attr);
    @GetMapping("/all-employees")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public String allEmployees(Model model);
    @GetMapping("/all-employees/{id}")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public ModelAndView delete(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException;
    @PostMapping("all-employees/{id}")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public String delete(@PathVariable Long id);
}
