package com.example.dealership1.repositories;

import com.example.dealership1.domain.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Long> {
}
