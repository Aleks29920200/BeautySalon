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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerIT {
    private static Long SERVICE_ID=39L;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "hasanis", roles={"ADMIN"})
    public void testAllServicesWithAdminRole() throws Exception {
        mockMvc.perform(get("/cosmeticService"))
                .andExpect(status().isOk()).
                andExpect(model().attributeExists("services"));
    }
    @Test
    @WithMockUser(username = "ali4o", roles={"USER"})
    public void testAllServicesWithUserRole() throws Exception {
        mockMvc.perform(get("/cosmeticService"))
                .andExpect(status().isOk()).
                andExpect(model().attributeExists("services"));
    }
    @Test
    @WithMockUser(username = "hasanis", roles={"ADMIN"})
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(get("/admin/cosmeticService/" + SERVICE_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/delete-service")).
                andExpect(model().attributeExists("service"));
    }
    @Test
    @WithMockUser(username = "hasanis", roles={"ADMIN"})
    public void testDeleteEmployeePost() throws Exception {
        mockMvc.perform(post("/admin/cosmeticService/" + SERVICE_ID))
                .andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/cosmeticService"));
    }
}
