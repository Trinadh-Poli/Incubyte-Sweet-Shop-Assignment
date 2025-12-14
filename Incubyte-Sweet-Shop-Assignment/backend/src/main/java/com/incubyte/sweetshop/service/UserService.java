package com.incubyte.sweetshop.service;

import org.springframework.stereotype.Service;

import com.incubyte.sweetshop.model.User;
import com.incubyte.sweetshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
            org.springframework.security.crypto.password.PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public com.incubyte.sweetshop.model.User register(com.incubyte.sweetshop.dto.RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already taken");
        }
        com.incubyte.sweetshop.model.User user = new com.incubyte.sweetshop.model.User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public com.incubyte.sweetshop.dto.AuthResponse login(com.incubyte.sweetshop.dto.LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new com.incubyte.sweetshop.dto.AuthResponse(token, user.getRole());
    }
}
