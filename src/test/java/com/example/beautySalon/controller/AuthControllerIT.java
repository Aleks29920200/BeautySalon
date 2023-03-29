package com.example.beautySalon.controller;

import com.example.beautySalon.domain.dto.binding.RegisterDto;
import com.example.beautySalon.domain.dto.service.UserDto;
import com.example.beautySalon.domain.dto.view.EmployeeViewDto;
import com.example.beautySalon.domain.dto.view.UserViewDto;
import com.example.beautySalon.domain.entity.User;
import com.example.beautySalon.services.EmployeeService;
import com.example.beautySalon.services.EmployeeServiceImpl;
import com.example.beautySalon.services.UserServiceImpl;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
   public void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("email", "pris@example.com").
                        param("username","pris").
                        param("firstName", "Presian").
                        param("lastName", "Iglov").
                        param("password", "123").
                        param("confirmPassword", "123")
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/login"));
    }
    @Test
    public void testRegistrationFail() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("email", "i").
                        param("username","i").
                        param("firstName", "I").
                        param("lastName", "D").
                        param("password", "123").
                        param("confirmPassword", "123")
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/register"));
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(post("/users/login").
                        param("username","alio").
                        param("password", "samsungga")
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/home"));
    }
    @Test
    public void testLoginFail() throws Exception {
        mockMvc.perform(post("/users/login").
                        param("username","elica").
                        param("password", "123")
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/login?error=true"));
    }
}
