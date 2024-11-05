package com.example.demo.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.model.User;
import com.example.demo.services.SecurityService;

public class ByCryptImp implements SecurityService{

    @Override
    public String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean checkEncode(String password, String encodePassword) {
        return new BCryptPasswordEncoder().matches(password, encodePassword);
    }

    @Override
    public String generateToken(User user) {
        try {
            // Define o algoritmo HMAC SHA256 para criar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256("Abobrinha123");
            return JWT.create()
                    .withIssuer("BATATA-api")
                    .withIssuedAt(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant())
                    .withExpiresAt(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(1).toInstant())
                    .withSubject(user.getEmail())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    @Override
    public String GetToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("Abobrinha123");
            return JWT.require(algorithm)
                    .withIssuer("BATATA-api") 
                    .build()
                    .verify(token) 
                    .getSubject(); 
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }
    
}
