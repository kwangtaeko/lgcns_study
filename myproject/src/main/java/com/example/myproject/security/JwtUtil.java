package com.example.myproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "3b9f6a2c1e4d9f7b81b0a9e38d1f5f6c54f9e4bcb1a7a3c7d9b6f8c0d2e3a9f4";

    public static String generateToken(String userId) {
        long expirationTime = 1000 * 60 * 60;

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
