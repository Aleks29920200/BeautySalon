package com.example.beautySalon.web;

import com.example.beautySalon.services.FileService;
import com.example.beautySalon.services.ServiceImpl;
import com.example.beautySalon.services.TransactionServiceImpl;
import com.example.beautySalon.domain.dto.binding.AddServiceDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.dto.view.ServiceViewDto;
import com.example.beautySalon.domain.entity.Service;
import com.example.beautySalon.repositories.ServiceRepo;
import com.example.beautySalon.services.UserServiceImpl;
import com.example.beautySalon.util.ImageUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class ServiceControllerImpl implements ServiceController{
    private ServiceImpl service;
    private ServiceRepo serviceRepo;
    private ModelMapper mapper;
    private UserServiceImpl userService;
    private FileService fileService;
    private TransactionServiceImpl transactionService;

    public ServiceControllerImpl(ServiceImpl service, ServiceRepo serviceRepo, ModelMapper mapper, UserServiceImpl userService, FileService fileService, TransactionServiceImpl transactionService) {
        this.service = service;
        this.serviceRepo = serviceRepo;
        this.mapper = mapper;
        this.userService = userService;
        this.fileService = fileService;
        this.transactionService = transactionService;
    }
    @Override
    public String addService(){
        return "/admin/add-service";
    }
    @Override
    public String service(@Valid @ModelAttribute("addServiceDto") AddServiceDto addServiceDto,
                          BindingResult bindingResult,
                          RedirectAttributes attr) throws IOException {

        if(bindingResult.hasErrors()){
            attr
                    .addFlashAttribute("addServiceDto", addServiceDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addServiceDto", bindingResult);
            return "redirect:/admin/add-service";
        }
        this.service.saveService(addServiceDto);
         return "redirect:/home";
    }
    @Override
    public String allServices(Model model){
        List<ServiceViewDto> all = this.service.allServices();
        model.addAttribute("services",all);
        return "/cosmeticService";
    }
    @Override
    public ModelAndView delete(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException {
        ServiceViewDto serviceById = this.service.findServiceById(id);
        model.setViewName("/admin/delete-service");
        model.addObject("service",serviceById);
        return model;
    }
    @Override
    public String delete(@PathVariable Long id){
        this.transactionService.deleteTransactionsByService_Id(id);
        this.service.deleteById(id);
        return "redirect:/cosmeticService";
    }
    @Override
    public String makeUp(){
        return "/makeUp";
    }
    @Override
    public String pedicure(){
        return "/pedicure";
    }
    @Override
    public String massages(){
        return "/massages";
    }

    @Override
    public String manicure(){
        return "/manicure";
    }
    @Override
    public String hairdressing(){
        return "/hairdressing";
    }
    @Override
    public ModelAndView info(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException {
        ServiceViewDto serviceById = this.service.findServiceById(id);
        model.setViewName("user/ManicureTypes");
        model.addObject("manicureType",serviceById);
        return model;
    }
    @ModelAttribute
    public AddServiceDto addServiceDto() {
        return new AddServiceDto();
    }
    @ModelAttribute(name="allServices")
    public List<Service> allServices() {
        return this.serviceRepo.findAll();
    }
    @ModelAttribute
    public ImageUtil imgUtil() {
        return new ImageUtil();
    }
}
