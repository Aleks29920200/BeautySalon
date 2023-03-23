package com.example.dealership1.repositories;

import com.example.dealership1.domain.entity.Role;
import com.example.dealership1.domain.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByAuthority(String role);
}
