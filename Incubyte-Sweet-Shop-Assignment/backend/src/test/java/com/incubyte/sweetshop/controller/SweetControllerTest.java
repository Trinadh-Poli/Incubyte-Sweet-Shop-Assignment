package com.incubyte.sweetshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;
import com.incubyte.sweetshop.service.JwtService;

@SpringBootTest
@AutoConfigureMockMvc
class SweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SweetRepository sweetRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    private String adminToken;

    @BeforeEach
    void setUp() {
        sweetRepository.deleteAll();
        // Generate a token for a mock admin user (skipping full auth flow for unit
        // speed if possible, but implementing auth check properly)
        // For simplicity in this test, we assume we can just pass a token.
        // Real integration might need a real user in DB.
        adminToken = "Bearer " + jwtService.generateToken("admin@example.com");
    }

    @Test
    void shouldAddSweetSuccessfully() throws Exception {
        Sweet sweet = new Sweet(null, "Chocolate Fudge", "Fudge", 5.0, 100);

        mockMvc.perform(post("/api/sweets")
                .header("Authorization", adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sweet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Chocolate Fudge"));
    }

    @Test
    void shouldPurchaseSweetSuccessfully() throws Exception {
        Sweet sweet = sweetRepository.save(new Sweet(null, "Lollipop", "Hard Candy", 1.0, 10));

        mockMvc.perform(post("/api/sweets/" + sweet.getId() + "/purchase")
                .header("Authorization", adminToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(9));
    }
}
