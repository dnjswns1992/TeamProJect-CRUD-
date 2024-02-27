package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private final Long EXPIRED_TIME = 1000000000L;
    private SecretKey secretKey;
    public static final String AUTH_HEADER = "Authorization";

    public JWTUtil(@Value("${spring.jwt.secret}")String originSecret) {

        secretKey = new SecretKeySpec(
                originSecret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm()
        );
    }

    public String getUsername(String token) {
        return retrievePayload(token).get("username", String.class);
    }

    public String getRole(String token) {
        return retrievePayload(token).get("role", String.class);
    }

    public boolean isExpiredToken(String token) {
        return retrievePayload(token).getExpiration().before(new Date());
    }

    public String createJwtToken(String username, String role) {

        return Jwts.builder()
                .issuer("JJH")
                .subject("JWT")
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + EXPIRED_TIME))
                .signWith(secretKey)
                .compact();
    }

    private Claims retrievePayload(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
