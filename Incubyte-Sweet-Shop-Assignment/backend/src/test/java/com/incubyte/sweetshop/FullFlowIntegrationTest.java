package com.incubyte.sweetshop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.sweetshop.dto.AuthResponse;
import com.incubyte.sweetshop.dto.LoginRequest;
import com.incubyte.sweetshop.dto.RegisterRequest;
import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class FullFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldRegisterLoginAndAddSweet() throws Exception {
        userRepository.deleteAll();

        // 1. Register
        RegisterRequest registerRequest = new RegisterRequest("test@test.com", "password", "123 St");
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk());

        // 2. Login
        LoginRequest loginRequest = new LoginRequest("test@test.com", "password");
        String responseString = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        AuthResponse authResponse = objectMapper.readValue(responseString, AuthResponse.class);
        String token = "Bearer " + authResponse.getToken();

        // 3. Add Sweet (Authorized)
        Sweet sweet = new Sweet(null, "Test Choco", "Test", 10.0, 50);
        mockMvc.perform(post("/api/sweets")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sweet)))
                .andExpect(status().isCreated());
    }
}
