package com.example.beautySalon.repositories;

import com.example.beautySalon.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction,Long> {

    Optional<List<Transaction>>findTransactionsByService_Id(Long id);
}

