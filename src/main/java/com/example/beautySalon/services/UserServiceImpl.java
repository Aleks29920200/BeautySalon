package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.view.UserViewDto;
import com.example.beautySalon.domain.dto.binding.RegisterDto;
import com.example.beautySalon.domain.dto.service.UserDto;
import com.example.beautySalon.domain.entity.Role;
import com.example.beautySalon.domain.entity.User;
import com.example.beautySalon.repositories.RoleRepo;
import com.example.beautySalon.repositories.UserRepo;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;

    private ModelMapper mapper;
    private RoleRepo roleRepo;
    private UserDto userDto;

    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, ModelMapper mapper, RoleRepo roleRepo, UserDto userDto, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.roleRepo = roleRepo;
        this.userDto = userDto;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public User findUserByEmail(String email) {
        return this.userRepo.findUserByEmail(email).orElse(null);
    }

    @Override
    public UserViewDto findUserByUsername(String username) {
        User user = this.userRepo.findUserByUsername(username).orElse(null);
        if(user==null){
            return null;
        }
        return mapper.map(user, UserViewDto.class);

    }
    @Override
    public void registerUser(RegisterDto userDto) {
        User map = this.mapper.map(userDto, User.class);
        map.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if(this.userRepo.count()==0){
           map.getAuthorities().add(this.roleRepo.findRoleByAuthority("BOSS").get());
        }else if(this.userRepo.count()==1){
            map.getAuthorities().add(this.roleRepo.findRoleByAuthority("ADMIN").get());
        }else{
            map.getAuthorities().add(this.roleRepo.findRoleByAuthority("USER").get());
        }
        this.userRepo.saveAndFlush(map);
    }
    @Override
    public Role findRoleByName(String name) {
        return this.roleRepo.findRoleByAuthority(name).orElse(null);
    }

    @Override
    public List<User> allUsers() {
        return this.userRepo.findAll();
    }


}
