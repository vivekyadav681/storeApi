package com.spring.storeapi.services;


import com.spring.storeapi.config.JwtConfig;
import com.spring.storeapi.entities.Role;
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


    public Jwt generateAccessToken(User user) {
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user) {
        final long tokenExpiration = 604800; //sevenDays
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    private Jwt generateToken(User user, long tokenExpiration) {
        var claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .add("name", user.getName())
                .add("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration*1000))
                .build();
        return new Jwt(claims, JwtConfig.getSecretKey());
    }





    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Jwt parseToken(String token) {
        try {
            var claims = getClaims(token);
            return new Jwt(claims, JwtConfig.getSecretKey());
        } catch (JwtException e) {
            return null;
        }
    }


}
