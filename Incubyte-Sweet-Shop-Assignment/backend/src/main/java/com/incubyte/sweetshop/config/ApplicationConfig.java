package com.incubyte.sweetshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.incubyte.sweetshop.repository.UserRepository;

@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.incubyte.sweetshop.model.User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new User(user.getEmail(), user.getPassword(),
                    java.util.Collections.singletonList(
                            new org.springframework.security.core.authority.SimpleGrantedAuthority(user.getRole())));
        };
    }
}
