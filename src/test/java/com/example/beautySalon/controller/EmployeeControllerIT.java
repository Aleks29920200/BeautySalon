package com.example.beautySalon.controller;

import com.example.beautySalon.services.EmployeeService;
import com.example.beautySalon.services.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;


    @Test
    public void testAddEmployee() throws Exception {
        mockMvc.perform(post("/boss/add-employee").
                        param("fullName", "Ivan Trunkatev").
                        param("age","20").
                        param("address", "ulica Struma 20").
                        param("startOfWorkingDay", "10:00").
                        param("endOfWorkingDay", "22:00").
                        param("salary", "2000").
                        param("identificationNumber", "skdjdksdsk").
                        param("email", "ivancho@gmail.com")
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/home"));
    }
}
