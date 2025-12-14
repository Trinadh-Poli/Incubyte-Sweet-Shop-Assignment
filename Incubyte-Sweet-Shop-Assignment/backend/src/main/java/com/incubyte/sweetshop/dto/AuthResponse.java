package com.incubyte.sweetshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@lombok.NoArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
}
