package com.example.beautySalon.controller;


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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
   public void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("email", "soldslkdks@example.com").
                        param("username","skdkdlslsl").
                        param("firstName", "slslslslsl").
                        param("lastName", "sllslsslsl").
                        param("password", "12345").
                        param("confirmPassword", "12345")
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/login"));
    }
    @Test
    @WithAnonymousUser
    public void testRegistrationFail() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("email", "aalkdkdsk@example.com").
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
    @WithAnonymousUser
    public void testLogin() throws Exception {
        mockMvc.perform(post("/users/login").
                        param("username","alio").
                        param("password", "12345")
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/home"));
    }
    @Test
    @WithAnonymousUser
    public void testLoginFail() throws Exception {
        mockMvc.perform(post("/users/login").
                        param("username","elica").
                        param("password", "123")
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/login?error=true"));
    }
}
