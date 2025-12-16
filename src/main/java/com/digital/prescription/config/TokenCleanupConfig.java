package com.digital.prescription.config;

import com.digital.prescription.repository.TokenRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@EnableScheduling
@Component
public class TokenCleanupConfig {
    private final TokenRepository tokenRepo;

    public TokenCleanupConfig(TokenRepository tokenRepo){
        this.tokenRepo = tokenRepo;
    }

    @Scheduled(cron = "0 0 * * * ?") // every hour
    public void deleteExpiredTokensEveryHour() {
        System.out.println("Deleting expired tokens...");
        tokenRepo.deleteAllExpiredTokens(LocalDateTime.now());
    }

//    @Scheduled(cron = "0 * * * * ?") // every minute
//    public void deleteExpiredTokensEveryMinute() {
//        System.out.println("Deleting expired tokens...");
//        tokenRepo.deleteAllExpiredTokens(LocalDateTime.now());
//    }

//    @Scheduled(fixedRate = 60_000) // every minute
//    public void deleteExpiredTokensEveryMinuteUsingFixedRate() {
//        System.out.println("Deleting expired tokens 2...");
//        tokenRepo.deleteAllExpiredTokens(LocalDateTime.now());
//    }
}
