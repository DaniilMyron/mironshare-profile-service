package com.miron.profileservice.infrastructure.config;

import com.miron.profileservice.infrastructure.persistence.entity.AccountEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtTokenService {
    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(AccountEntity user) {
        Instant now = Instant.now();
        long expiry = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("http://localhost:8083")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(user.getId().toString())
                .claim("username", user.getUsername())
                .claim("roles", List.of("USER"))
                .audience(List.of("direct-service", "recommendation-service"))
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
