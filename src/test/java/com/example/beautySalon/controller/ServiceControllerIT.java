package com.example.beautySalon.controller;

import com.example.beautySalon.domain.dto.view.ServiceViewDto;
import com.example.beautySalon.domain.entity.Service;
import com.example.beautySalon.services.ServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerIT {
    private static Long SERVICE_ID=5L;
    private static Long SERVICE_ID_1=6L;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "hasanis", roles={"ADMIN"})
    public void testAddServiceGet() throws Exception {
        mockMvc.perform(get("/admin/add-service")).
                andExpect(status().is2xxSuccessful());
    }
    @Test
    @WithMockUser(username = "hasanis", roles={"ADMIN"})
    public void testAddService() throws Exception {
        mockMvc.perform(post("/admin/add-service").
                        param("name", "Mexican Manicure").
                        param("price","24.6").
                        param("category", "MANICURE").
                        param("info","lddldlsdddsjsjsjsjsksks")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/home"));
    }
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
    public void testDeleteService() throws Exception {
        mockMvc.perform(get("/admin/cosmeticService/" + SERVICE_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/delete-service")).
                andExpect(model().attributeExists("service"));
    }
    @Test
    @WithMockUser(username = "hasanis", roles={"ADMIN"})
    public void testDeleteServicePost() throws Exception {
        mockMvc.perform(post("/admin/cosmeticService/" + SERVICE_ID).with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
    @Test
    public void testInfoAboutService() throws Exception {
        mockMvc.perform(get("/manicure/" + SERVICE_ID_1).with(csrf()))
                .andExpect(view().name("user/ManicureTypes"))
                .andExpect(model().attributeExists("manicureType"));
    }
    @Test
    public void manicureService() throws Exception {
        mockMvc.perform(get("/manicure"))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void pedicureService() throws Exception {
        mockMvc.perform(get("/pedicure"))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void hairdressingService() throws Exception {
        mockMvc.perform(get("/hairdressing"))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void massagesService() throws Exception {
        mockMvc.perform(get("/massages"))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void makeUpService() throws Exception {
        mockMvc.perform(get("/makeUp"))
                .andExpect(status().is2xxSuccessful());
    }
}
