package com.example.beautySalon.services;

import com.example.beautySalon.domain.entity.Role;
import com.example.beautySalon.domain.entity.RoleName;
import com.example.beautySalon.repositories.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }



    @Override
    public void seedAllRoles() {
        if(roleRepo.count()==0) {
            Role admin = new Role();
            admin.setAuthority(RoleName.ADMIN.name());
            this.roleRepo.save(admin);
            Role user = new Role();
            user.setAuthority(RoleName.USER.name());
            this.roleRepo.save(user);
            Role boss = new Role();
            boss.setAuthority(RoleName.BOSS.name());
            this.roleRepo.save(boss);
        }
    }
}
