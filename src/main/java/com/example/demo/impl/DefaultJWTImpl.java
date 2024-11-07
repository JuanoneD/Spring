package com.example.demo.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.example.demo.dto.TokenBody;
import com.example.demo.services.JwtService;

public class DefaultJWTImpl implements JwtService<TokenBody>{
    private final String SECRET_KEY = "ouqebfdouiebfouqewfnuoqewnhfouewnfouewnh";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    @Override
    public String get(TokenBody token) {
        var claims = new HashMap<String, Object>();
        
        claims.put("id", token.getId());
        claims.put("email", token.getEmail());
        claims.put("name", token.getName());

        return get(claims);
    }

    @Override
    public TokenBody validate(String jwt) {
        try
        {
            var map = validateJwt(jwt);

            TokenBody token = new TokenBody();
            token.setId(Long.parseLong(map.get("id").toString()));
            token.setEmail(map.get("email").toString());
            token.setName(map.get("name").toString());

            return token;
        }
        catch (Exception ex)
        {
            // ex.printStackTrace();
            return null;
        }
    }

    private String get(Map<String, Object> customClaims) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
            .claims()
                .add(customClaims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .and()
            .signWith(key)
            .compact();
    }

    private Map<String, Object> validateJwt(String jwt) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(jwt)
            .getPayload();
        
        return new HashMap<>(claims);
    }
}
