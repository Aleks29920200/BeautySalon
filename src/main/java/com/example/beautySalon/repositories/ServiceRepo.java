package com.example.beautySalon.repositories;

import com.example.beautySalon.domain.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepo extends JpaRepository<Service,Long> {
   Service findServiceById(Long id);
   void deleteById(Long id);
   Service findServiceByName(String name);
}
