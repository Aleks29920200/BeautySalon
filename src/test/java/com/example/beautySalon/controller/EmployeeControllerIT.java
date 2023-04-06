package com.example.beautySalon.controller;

import com.example.beautySalon.domain.dto.view.EmployeeViewDto;
import com.example.beautySalon.repositories.UserRepo;
import com.example.beautySalon.services.EmployeeService;
import com.example.beautySalon.services.EmployeeServiceImpl;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIT {
    private static final Long EMPLOYEE_ID = 6L;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "alio",password = "123",roles={"BOSS"})
    public void testAddEmployee() throws Exception {
        mockMvc.perform(get("/boss/add-employee")).
                andExpect(status().is2xxSuccessful());
    }
    @Test
    @WithMockUser(username = "alio",password = "123",roles={"BOSS"})
    public void testAddEmployeeFail() throws Exception {
        mockMvc.perform(post("/boss/add-employee").
                        param("fullName", "I").
                        param("age","-1").
                        param("address", "").
                        param("startOfWorkingDay", "").
                        param("endOfWorkingDay", "").
                        param("salary", "-1").
                        param("identificationNumber", "j").
                        param("email", "ivanIvanov@examp.com")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/boss/add-employee"));
    }
    @Test
    @WithMockUser(username = "alio",password = "123" ,roles={"BOSS"})
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(get("/boss/all-employees/" + EMPLOYEE_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("/boss/delete-employee")).
                andExpect(model().attributeExists("employee"));
    }
    @Test
    @WithMockUser(username = "alio",password = "123",roles={"BOSS"})
    public void testDeleteEmployeePost() throws Exception {
        mockMvc.perform(post("/boss/all-employees/" + EMPLOYEE_ID).with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
}
