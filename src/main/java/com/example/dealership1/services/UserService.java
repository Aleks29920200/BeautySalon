package com.example.dealership1.services;


import com.example.dealership1.domain.dto.binding.RegisterDto;
import com.example.dealership1.domain.dto.view.UserViewDto;
import com.example.dealership1.domain.entity.Role;
import com.example.dealership1.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    User findUserByEmail(String email);

    UserViewDto findUserByUsername(String email);

    void registerUser(RegisterDto userDto);

    Role findRoleByName(String name);

    List<User> allUsers();


}
