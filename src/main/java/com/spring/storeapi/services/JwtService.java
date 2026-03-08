package com.spring.storeapi.services;


import com.spring.storeapi.config.JwtConfig;
import com.spring.storeapi.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig jwtConfig;


    public String generateAccessToken(User user) {
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    public String generateRefreshToken(User user) {
        final long tokenExpiration = 604800; //sevenDays
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    private String generateToken(User user, long tokenExpiration) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration*1000))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public boolean validatingToken(String token) {
        try {
            var claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        }
        catch (JwtException e) {
            return false;
        }


    }

    public Long getUserIdFromToken(String token) {
        var claims = getClaims(token);
        return Long.valueOf(claims.getSubject());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
