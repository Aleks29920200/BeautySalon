package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/user/")
public interface TransactionController {

    @PostMapping("add-transaction/cosmeticService/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String select(@PathVariable Long id, ModelAndView model, Principal principal) throws ObjectNotFoundException;
    @GetMapping("all-transactions")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String allTransactions(Model model, Principal principal);
    @GetMapping("all-transactions/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView deleteTransaction(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException;

    @PostMapping("all-transactions/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deleteTransaction(@PathVariable Long id);
}
