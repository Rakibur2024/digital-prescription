package com.digital.prescription.config;


import com.digital.prescription.entities.Role;
import com.digital.prescription.entities.Users;
import com.digital.prescription.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserDetailsRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Only create admin if not already present
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin")); // bcrypt encoded
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);

                System.out.println("Default admin user created -> username: admin, password: admin");
            }
            if (userRepository.findByUsername("doctor").isEmpty()) {
                Users doctor = new Users();
                doctor.setUsername("doctor");
                doctor.setPassword(passwordEncoder.encode("doctor"));
                doctor.setRole(Role.DOCTOR);
                userRepository.save(doctor);
                System.out.println("✅ Default DOCTOR created → username: doctor, password: doctor");
            }
            if (userRepository.findByUsername("assistant").isEmpty()) {
                Users assistant = new Users();
                assistant.setUsername("assistant");
                assistant.setPassword(passwordEncoder.encode("assistant"));
                assistant.setRole(Role.ASSISTANT);
                userRepository.save(assistant);
                System.out.println("✅ Default ASSISTANT created → username: assistant, password: assistant");
            }
            System.out.println("User initialization completed.");
        };
    }

}
