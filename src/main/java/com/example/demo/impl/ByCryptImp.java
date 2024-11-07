package com.example.demo.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
}
