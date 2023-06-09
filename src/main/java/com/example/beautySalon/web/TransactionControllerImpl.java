package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.binding.TransactionCreatedEvent;
import com.example.beautySalon.domain.dto.service.TransactionDto;
import com.example.beautySalon.domain.dto.view.TransactionViewDto;
import com.example.beautySalon.services.TransactionServiceImpl;
import com.example.beautySalon.domain.dto.binding.AddTransactionDto;
import com.example.beautySalon.domain.dto.service.ServiceDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.services.BillServiceImpl;
import com.example.beautySalon.services.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user/")
public class TransactionControllerImpl implements TransactionController{
    private ServiceImpl service;
    private TransactionServiceImpl transactionService;
    private ModelMapper mapper;
    private BillServiceImpl billService;

    public TransactionControllerImpl(ServiceImpl service, TransactionServiceImpl transactionService, ModelMapper mapper, BillServiceImpl billService) {
        this.service = service;
        this.transactionService = transactionService;
        this.mapper = mapper;
        this.billService = billService;
    }
    @Override
    public String select(@PathVariable Long id, ModelAndView model, Principal principal) throws ObjectNotFoundException {
        AddTransactionDto fillTransactionDto = transactionService.fillTransactionDto(principal.getName(), mapper.map(this.service.findServiceById(id), ServiceDto.class));
        model.setViewName("user/add-transaction");
        transactionService.saveTransaction(fillTransactionDto);
        model.addObject("transaction",fillTransactionDto);
        return "redirect:/user/all-transactions";
    }
    @Override
    public String allTransactions(Model model,Principal principal){
        List<TransactionViewDto> all = this.transactionService.allTransactions(principal.getName()).stream().map(e->mapper.map(e,TransactionViewDto.class)).collect(Collectors.toList());
        model.addAttribute("transactions",all);
        return "/user/all-transactions";
    }
    @Override
    public ModelAndView deleteTransaction(@PathVariable Long id, ModelAndView model) throws ObjectNotFoundException {
        TransactionViewDto transactionView = this.transactionService.findTransactionById(id);
        model.setViewName("/user/delete-transaction");
        model.addObject("transaction",transactionView);
        return model;
    }

    @Override
    public String deleteTransaction(@PathVariable Long id){
        this.transactionService.deleteById(id);
        return "redirect:/user/all-transactions";
    }
    @ModelAttribute(name="allTransactions")
    public List<TransactionViewDto> allTransactions(Principal principal) {
        return this.transactionService.allTransactions(principal.getName());
    }
    @ModelAttribute(name="addTransaction")
    public AddTransactionDto addTransaction() {
        return new AddTransactionDto();
    }
}
