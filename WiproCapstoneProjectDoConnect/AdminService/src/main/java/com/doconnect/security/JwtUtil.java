package com.doconnect.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // Marks this class as a Spring Bean for dependency injection
public class JwtUtil {

    private final String SECRET_KEY = "doconnect_secret_doconnect_secret"; // Secret key (must be at least 32 chars for HS256)
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // Token expiration time = 1 hour

    private Key getSigningKey() { // Generate signing key from secret
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate JWT token with email and role
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email) // Set subject as email
                .claim("role", role) // Add custom claim: role
                .setIssuedAt(new Date()) // Issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiration time
                .signWith(getSigningKey()) // Sign token with secret key
                .compact();
    }

    // Extract claims (payload) from JWT token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Validate with secret key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract username (email) from token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Validate token by checking username and expiration
    public boolean validateToken(String token, String email) {
        return email.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
