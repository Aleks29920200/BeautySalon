package com.example.beautySalon.services;


import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.dto.service.BillDto;
import com.example.beautySalon.domain.dto.service.ServiceDto;
import com.example.beautySalon.domain.entity.Bill;
import com.example.beautySalon.domain.entity.Comment;
import com.example.beautySalon.domain.entity.User;
import com.example.beautySalon.repositories.BillRepo;
import com.example.beautySalon.repositories.CommentRepository;
import com.example.beautySalon.repositories.ServiceRepo;
import com.example.beautySalon.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {
    private BillRepo billRepo;
    private ServiceImpl service;
    private ModelMapper mapper;

    public BillServiceImpl(BillRepo billRepo, ServiceImpl service, ModelMapper mapper) {
        this.billRepo = billRepo;
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<Bill> allBills() {
        return this.billRepo.findAll();
    }

    @Override
    public Bill findById(Long id) {
        return this.billRepo.findById(id).orElse(null);
    }

    @Override
    public Bill addBill(ServiceDto serviceDto) {
        BillDto billDto=new BillDto();
        billDto.setPrice(serviceDto.getPrice());
        billDto.setAmount((float) this.service.allServices().stream().filter(e -> e.getId().equals(serviceDto.getId())).count());
        return billRepo.saveAndFlush(mapper.map(billDto, Bill.class));
    }
}
