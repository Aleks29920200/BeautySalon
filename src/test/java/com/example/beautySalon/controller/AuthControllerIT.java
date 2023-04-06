package com.example.beautySalon.controller;


import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
                        param("email", "sodskdkkskdd@example.com").
                        param("username","soleokekekekkdkdk").
                        param("firstName", "dkdkdkdlelele").
                        param("lastName", "skdkdklsdldld").
                        param("password", "12345").
                        param("confirmPassword", "12345")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/login"));
    }
    @Test
    public void testRegistrationFail() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("email", "aalkdkdsk@example.com").
                        param("username","i").
                        param("firstName", "I").
                        param("lastName", "D").
                        param("password", "123").
                        param("confirmPassword", "123")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/register"));
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(post("/users/login").
                        param("username","alio").
                        param("password", "123")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/home"));
    }
    @Test
    public void testLoginFail() throws Exception {
        mockMvc.perform(post("/users/login").
                        param("username","elica").
                        param("password", "123")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/login?error=true"));
    }
}
