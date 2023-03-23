package com.example.dealership1.repositories;

import com.example.dealership1.domain.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Service,Long> {
   Service findServiceById(Long id);
   void deleteById(Long id);
   Service findServiceByName(String name);
}
