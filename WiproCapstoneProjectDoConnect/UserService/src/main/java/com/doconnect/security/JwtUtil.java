package com.doconnect.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // Marks this as a Spring Bean for dependency injection
public class JwtUtil {

    private final String SECRET = "MySecretKeyForJwtGenerationRushitha123!"; // Secret key for signing tokens
    private final long EXPIRATION = 1000 * 60 * 60; // Token validity = 1 hour

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes()); // Generate signing key from secret
    }

    // Generate JWT token with username and role
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username) // Set subject as username
                .claim("role", role) // Add custom claim: role
                .setIssuedAt(new Date()) // Token issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // Expiry time
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign with secret key
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extract role from token
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // Validate token (username match + not expired)
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Check if token has expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Extract all claims (payload) from token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Set signing key
                .build()
                .parseClaimsJws(token) // Parse token
                .getBody(); // Return claims
    }
}
