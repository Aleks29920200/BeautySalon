package com.example.beautySalon.repositories;

import com.example.beautySalon.domain.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Long> {
}
