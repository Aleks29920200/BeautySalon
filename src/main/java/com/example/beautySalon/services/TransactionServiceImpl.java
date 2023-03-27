package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.TransactionCreatedEvent;
import com.example.beautySalon.domain.dto.service.TransactionDto;
import com.example.beautySalon.domain.dto.view.TransactionViewDto;
import com.example.beautySalon.domain.dto.view.UserViewDto;
import com.example.beautySalon.domain.dto.binding.AddTransactionDto;
import com.example.beautySalon.domain.dto.service.BillDto;
import com.example.beautySalon.domain.dto.service.ServiceDto;
import com.example.beautySalon.domain.dto.service.UserDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.entity.BaseEntity;
import com.example.beautySalon.domain.entity.Transaction;
import com.example.beautySalon.domain.entity.User;
import com.example.beautySalon.repositories.TransactionRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepo transactionRepo;
    private ModelMapper mapper;

    private ServiceImpl service;
    private BillServiceImpl billService;
    private UserServiceImpl userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);
    private final ApplicationEventPublisher appEventPublisher;
    TransactionDto transactionDto=new TransactionDto();
    public TransactionServiceImpl(TransactionRepo transactionRepo, ModelMapper mapper, ServiceImpl service, BillServiceImpl billService, UserServiceImpl userService, ApplicationEventPublisher appEventPublisher) {
        this.transactionRepo = transactionRepo;
        this.mapper = mapper;
        this.service = service;
        this.billService = billService;
        this.userService = userService;
        this.appEventPublisher = appEventPublisher;
    }

    @Override
    public List<TransactionViewDto> allTransactions(String username) {
        UserViewDto userByUsername = this.userService.findUserByUsername(username);
        return this.transactionRepo.findAll().stream().filter(e->e.getUser().getId().equals(userByUsername.getId())).map(e->mapper.map(e,TransactionViewDto.class)).collect(Collectors.toList());
    }
    @Override
    public AddTransactionDto fillTransactionDto(String username, ServiceDto map) {
        AddTransactionDto transaction=new AddTransactionDto();
        transaction.setStartDate(LocalDateTime.now().toString());
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
        transaction.setExpirationDate(dateTime.toString());
        transaction.setServiceName(map);
        transaction.setBillDto(mapper.map(this.billService.addBill(map), BillDto.class));
        transaction.setUser(mapper.map(this.userService.findUserByUsername(username), UserDto.class));
        return transaction;
    }
    @Override
    public TransactionViewDto findTransactionById(Long id) throws ObjectNotFoundException {
        Transaction transaction1 = this.transactionRepo.findById(id).orElse(null);
        if(transaction1==null){
            throw new ObjectNotFoundException(id,"Transaction");
        }
        TransactionViewDto transaction = mapper.map(transaction1, TransactionViewDto.class);
        if(transaction==null){
            throw new ObjectNotFoundException(id,"Transaction");
        }
        return transaction;
    }

    @Override
    public void deleteById(Long id) {
        this.transactionRepo.deleteById(id);
    }

    @Override
    public void saveTransaction(AddTransactionDto fillTransactionDto) {
        Transaction transaction=new Transaction();
        transaction.setStartDate(LocalDateTime.parse(fillTransactionDto.getStartDate()));
        transaction.setExpirationDate(LocalDateTime.parse(fillTransactionDto.getExpirationDate()));
        transaction.setService(mapper.map(fillTransactionDto.getServiceName(), com.example.beautySalon.domain.entity.Service.class));
        transaction.setBill(this.billService.findById(fillTransactionDto.getBillDto().getId()));
        transaction.setUser(mapper.map(this.userService.findUserByUsername(fillTransactionDto.getUser().getUsername()),User.class));
        Transaction transaction1 = this.transactionRepo.saveAndFlush(transaction);
        transactionDto.addProductId(transaction1.getId());
        event(transactionDto);
    }

    public void event(TransactionDto transaction1) {
        TransactionCreatedEvent orderCreatedEvent =
                new TransactionCreatedEvent(transaction1.getAllProductIDs());
        LOGGER.info("Transaction was created");
        appEventPublisher.publishEvent(orderCreatedEvent);
    }

    @Override
    public void deleteTransactionsByService_Id(Long id) {
        List<TransactionViewDto> transactionsByService_id = findTransactionsByService_Id(id);
        IntStream.range(0, transactionsByService_id.size()).forEach(i -> this.transactionRepo.deleteById(transactionsByService_id.get(i).getId()));
    }
    @Override
    public List<TransactionViewDto> findTransactionsByService_Id(Long id) {
        return Objects.requireNonNull(this.transactionRepo.findTransactionsByService_Id(id).
                        orElse(null)).
                stream().
                map(e -> mapper.map(e, TransactionViewDto.class)).toList();
    }
}
