package com.example.beautySalon.repositories;

import com.example.beautySalon.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByAuthority(String role);
}
