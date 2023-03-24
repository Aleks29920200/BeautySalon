package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.service.ServiceDto;
import com.example.beautySalon.domain.entity.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {

    List<Bill> allBills();
    Bill findById(Long id);
    Bill addBill(ServiceDto serviceDto);
}
