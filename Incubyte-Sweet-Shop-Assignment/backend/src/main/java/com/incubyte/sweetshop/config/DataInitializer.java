package com.incubyte.sweetshop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.model.User;
import com.incubyte.sweetshop.repository.SweetRepository;
import com.incubyte.sweetshop.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, SweetRepository sweetRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Seed Admin if not exists
            if (userRepository.findByEmail("admin@sweetshop.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@sweetshop.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setAddress("Headquarters");
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
                System.out.println("Admin user seeded.");
            }

            // Seed Sweets (Force refresh for price updates)
            sweetRepository.deleteAll();

            sweetRepository.save(new Sweet(null, "Rainbow Lollipop", "Lollipops", 20.00, 50));
            sweetRepository.save(new Sweet(null, "Dark Chocolate Bar", "Chocolates", 150.00, 20));
            sweetRepository.save(new Sweet(null, "Gummy Bears", "Gummies", 50.00, 30));
            sweetRepository.save(new Sweet(null, "Sour Worms", "Gummies", 50.00, 30));
            sweetRepository.save(new Sweet(null, "Peanut Butter Cup", "Chocolates", 80.00, 40));
            System.out.println("Sweets seeded.");
        };
    }
}
