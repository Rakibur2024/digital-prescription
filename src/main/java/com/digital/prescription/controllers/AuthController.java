package com.digital.prescription.controllers;


import com.digital.prescription.entities.AuthRequest;
import com.digital.prescription.service.CustomUserDetailsService;
import com.digital.prescription.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager; //Field Injection

    @Autowired
    CustomUserDetailsService customUserDetailsService;

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
}