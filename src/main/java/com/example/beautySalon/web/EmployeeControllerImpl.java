package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.service.EmployeeDto;
import com.example.beautySalon.domain.dto.view.EmployeeViewDto;
import com.example.beautySalon.services.EmployeeServiceImpl;
import com.example.beautySalon.domain.dto.binding.AddEmployeeDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalTime;
import java.util.List;

@Controller
public class EmployeeControllerImpl implements EmployeeController{
    private EmployeeServiceImpl employeeService;
    private ModelMapper mapper;

    public EmployeeControllerImpl(EmployeeServiceImpl employeeService, ModelMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }
    @Override
    public String addEmployee(){
        return "/boss/add-employee";
    }
    @Override
    public String employeePost(@Valid @ModelAttribute("addEmployeeDto") AddEmployeeDto addEmployeeDto,
                          BindingResult bindingResult,
                          RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            attr
                    .addFlashAttribute("addEmployeeDto", addEmployeeDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addEmployeeDto", bindingResult);
            return "redirect:/boss/add-employee";
        }
       this.employeeService.addEmployee(addEmployeeDto);
        return "redirect:/home";
    }
    @Override
    public String allEmployees(Model model){
        List<EmployeeViewDto> all =this.employeeService.allEmployees();
        model.addAttribute("employees",all);
        return "/boss/all-employees";
    }
    @Override
    public ModelAndView delete(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException {
        EmployeeDto employeeById = this.employeeService.findEmployeeById(id);
        model.setViewName("/boss/delete-employee");
        model.addObject("employee",employeeById);
        return model;
    }
    @Override
    public String delete(@PathVariable Long id){
        this.employeeService.deleteById(id);
        return "redirect:/boss/all-employees";
    }
    @ModelAttribute(name="employees")
    public List<EmployeeViewDto> allServices() {
        return this.employeeService.allEmployees();
    }
    @ModelAttribute("start")
    public LocalTime time() {
        return addEmployeeDto().getStartOfWorkingDay();
    }
    @ModelAttribute("end")
    public LocalTime timeEnd() {
        return addEmployeeDto().getEndOfWorkingDay();
    }
    @ModelAttribute
    public AddEmployeeDto addEmployeeDto() {
        return new AddEmployeeDto();
    }
}
