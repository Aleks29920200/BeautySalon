package com.example.dealership1.web;

import com.example.dealership1.domain.dto.binding.AddServiceDto;
import com.example.dealership1.domain.dto.error.ObjectNotFoundException;
import com.example.dealership1.domain.dto.view.ServiceViewDto;
import com.example.dealership1.domain.entity.Service;
import com.example.dealership1.repositories.ServiceRepo;
import com.example.dealership1.services.*;
import com.example.dealership1.util.ImageUtil;
import com.example.dealership1.util.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

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
        return "/admin/cosmeticService";
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
        return "redirect:/admin/cosmeticService";
    }
    @Override
    public String makeUp(){
        return "/user/makeUp";
    }
    @Override
    public String pedicure(){
        return "/user/pedicure";
    }
    @Override
    public String massages(){
        return "/user/massages";
    }
    @Override
    public String manicure(){
        return "/user/manicure";
    }
    @Override
    public String hairdressing(){
        return "/user/hairdressing";
    }
    @Override
    public ModelAndView info(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException {
        ServiceViewDto serviceById = this.service.findServiceById(id);
        model.setViewName("/user/ManicureTypes");
        model.addObject("manicureType",serviceById);
        model.addObject("imgUtil", new ImageUtil());
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


}
