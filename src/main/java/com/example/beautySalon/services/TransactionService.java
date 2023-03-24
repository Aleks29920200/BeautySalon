package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.AddTransactionDto;
import com.example.beautySalon.domain.dto.service.ServiceDto;
import com.example.beautySalon.domain.dto.view.TransactionViewDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<TransactionViewDto> allTransactions(String username);


    AddTransactionDto fillTransactionDto(String username, ServiceDto map);

    TransactionViewDto findTransactionById(Long id) throws ObjectNotFoundException;

    void deleteById(Long id);

    void saveTransaction(AddTransactionDto fillTransactionDto);
    void deleteTransactionsByService_Id(Long id);

    List<TransactionViewDto> findTransactionsByService_Id(Long id);
}
