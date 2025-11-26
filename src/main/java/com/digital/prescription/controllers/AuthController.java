package com.digital.prescription.controllers;


import com.digital.prescription.entities.AuthRequest;
import com.digital.prescription.entities.Users;
import com.digital.prescription.repository.UserDetailsRepository;
import com.digital.prescription.service.CustomUserDetailsService;
import com.digital.prescription.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager; //Field Injection

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserDetailsRepository userDetailsRepo;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
            final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
            return jwtUtil.generateToken(userDetails);
        } catch (Exception e){
            throw e;
        }
    }
    @PostMapping("/authenticateAndGetUserDetails")
    public ResponseEntity<?> generateTokenAndGetUserDetails(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());

            // Generate token
            String token = jwtUtil.generateToken(userDetails);

            // Get user info
            Users user = userDetailsRepo.findByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create response object
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                    "id", user.getId(),
                    "username", user.getUsername(),
                    "role", user.getRole().name()
            ));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/getUserData/{userName}")
    public ResponseEntity<?> getUserDataByUserName(@PathVariable("userName") String userName){
        try {
            // Get user info
            Users user = userDetailsRepo.findByUsername(userName)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create response object
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("role", user.getRole().name());
            response.put("doctorId", user.getDoctorId());
            response.put("assistantId", user.getAssistantId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}