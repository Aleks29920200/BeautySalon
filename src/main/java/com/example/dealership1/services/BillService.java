package com.example.dealership1.services;

import com.example.dealership1.domain.dto.service.ServiceDto;
import com.example.dealership1.domain.entity.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {

    List<Bill> allBills();
    Bill findById(Long id);
    Bill addBill(ServiceDto serviceDto);
}
