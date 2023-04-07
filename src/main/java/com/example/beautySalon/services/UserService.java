package com.example.beautySalon.services;


import com.example.beautySalon.domain.dto.binding.EditUser;
import com.example.beautySalon.domain.dto.view.UserViewDto;
import com.example.beautySalon.domain.dto.binding.RegisterDto;
import com.example.beautySalon.domain.entity.Role;
import com.example.beautySalon.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    User findUserByEmail(String email);

    User findUserById(Long id);
    UserViewDto findUserByUsername(String email);

    void registerUser(RegisterDto userDto);

    Role findRoleByName(String name);

    List<UserViewDto> allUsers();



    void editUser(EditUser editUser,String username);
}
