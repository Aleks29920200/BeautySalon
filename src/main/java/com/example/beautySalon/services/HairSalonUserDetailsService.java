package com.example.beautySalon.services;


import com.example.beautySalon.repositories.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;
public class HairSalonUserDetailsService implements UserDetailsService {
    private UserRepo userRepo;

    public HairSalonUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user= userRepo.findUserByUsername(username);
        User user1 = user
                .map(u -> new User(
                        u.getUsername(),
                        u.getPassword(),
                        u.getAuthorities().stream()
                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getAuthority()))
                                .collect(Collectors.toSet())
                )).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
        return user1;
    }

}


