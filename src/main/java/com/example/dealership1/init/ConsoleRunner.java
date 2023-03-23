package com.example.dealership1.init;

import com.example.dealership1.services.RoleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private RoleServiceImpl roleService;

    public ConsoleRunner(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.seedAllRoles();
    }
}
