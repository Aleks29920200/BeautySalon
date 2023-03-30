package com.example.beautySalon.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerIT {
    private static final Long TRANSACTION_ID = 70L;
    private static final Long SERVICE_ID = 41L;
    @Autowired
    private MockMvc mockMvc;
    @Test
    @WithMockUser(username = "ali4o", roles={"USER"})
    public void testAddTransaction() throws Exception {
        mockMvc.perform(post("/user/add-transaction/cosmeticService/" + SERVICE_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/add-transaction")).
                andExpect(model().attributeExists("transaction"));
    }
    @Test
    @WithMockUser(username = "ali4o", roles={"USER"})
    public void testDeleteTransaction() throws Exception {
        mockMvc.perform(get("/user/all-transactions/" + TRANSACTION_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/delete-transaction")).
                andExpect(model().attributeExists("transaction"));

    }
    @Test
    @WithMockUser(username = "ali4o", roles={"USER"})
    public void testDeleteTransactionPost() throws Exception {
        mockMvc.perform(post("/user/all-transactions/" + TRANSACTION_ID))
                .andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/user/all-transactions"));
    }
}
