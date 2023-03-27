package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.binding.AddServiceDto;
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

import java.io.IOException;
@Controller
public interface ServiceController {
    @GetMapping("/admin/add-service")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addService();
    @PostMapping("/admin/add-service")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String service(@Valid @ModelAttribute("addServiceDto") AddServiceDto addServiceDto,
                          BindingResult bindingResult,
                          RedirectAttributes attr) throws IOException;
    @GetMapping("/cosmeticService")
    public String allServices(Model model);
    @GetMapping("/admin/cosmeticService/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException;
    @PostMapping("/admin/cosmeticService/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable Long id);
    @GetMapping("/makeUp")
    public String makeUp();
    @GetMapping("/pedicure")
    public String pedicure();
    @GetMapping("/massages")
    public String massages();

    @GetMapping("/manicure")
    public String manicure();
    @GetMapping("/hairdressing")
    public String hairdressing() throws ObjectNotFoundException;
    @GetMapping("/manicure/{id}")
    public ModelAndView info(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException;
}
