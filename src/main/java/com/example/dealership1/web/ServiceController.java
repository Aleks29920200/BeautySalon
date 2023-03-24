package com.example.dealership1.web;

import com.example.dealership1.domain.dto.binding.AddServiceDto;
import com.example.dealership1.domain.dto.error.ObjectNotFoundException;
import com.example.dealership1.domain.dto.view.ServiceViewDto;
import com.example.dealership1.util.ImageUtil;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

public interface ServiceController {
    @GetMapping("/admin/add-service")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addService();
    @PostMapping("/admin/add-service")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String service(@Valid @ModelAttribute("addServiceDto") AddServiceDto addServiceDto,
                          BindingResult bindingResult,
                          RedirectAttributes attr) throws IOException;
    @GetMapping("/admin/cosmeticService")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allServices(Model model);
    @GetMapping("/admin/cosmeticService/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException;
    @PostMapping("/admin/cosmeticService/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable Long id);
    @GetMapping("/user/makeUp")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String makeUp();
    @GetMapping("/user/pedicure")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String pedicure();
    @GetMapping("/user/massages")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String massages();
    @GetMapping("/user/manicure")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String manicure();
    @GetMapping("/user/hairdressing")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String hairdressing();
    @GetMapping("/user/manicure/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView info(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException;
}
