package com.example.beautySalon.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerIT {
    @Autowired
   private MockMvc mockMvc;
    @Test
    @WithMockUser(username = "ivan777",roles = "{USER}")
    public void testProfile() throws Exception {
        mockMvc.perform(get("/profile")).
                andExpect(status().is2xxSuccessful());
    }
    @Test
    @WithMockUser(username = "ivan777",roles = "{USER}")
    public void testProfilePost() throws Exception {
        mockMvc.perform(post("/profile/edit").
                        param("email", "ivan@example.com").
                        param("username","ivan777").
                        param("firstName", "ivan").
                        param("lastName", "ivanov").
                        param("password", "12345").
                        param("confirmPassword", "12345")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/profile"));
    }
}
