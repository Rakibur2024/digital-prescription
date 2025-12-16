package com.digital.prescription.service;

import com.digital.prescription.entities.Token;
import com.digital.prescription.repository.TokenRepository;
import com.digital.prescription.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    TokenRepository tokenRepo;

    public void saveNewToken(String token, Long userId) {
        Token newToken = new Token();
        newToken.setToken(token);
        newToken.setUserId(userId);
        newToken.setValid(true);
        newToken.setCreatedAt(jwtUtil.extractIssuedAt(token));
        newToken.setExpiresAt(jwtUtil.extractExpiration(token));

        tokenRepo.save(newToken);
    }
}
