package com.example.dealership1.services;

import com.example.dealership1.domain.dto.binding.RegisterDto;
import com.example.dealership1.domain.dto.service.UserDto;
import com.example.dealership1.domain.dto.view.UserViewDto;
import com.example.dealership1.domain.entity.Role;
import com.example.dealership1.domain.entity.User;
import com.example.dealership1.repositories.RoleRepo;
import com.example.dealership1.repositories.UserRepo;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        return mapper.map(this.userRepo.findUserByUsername(username).get(), UserViewDto.class);

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
