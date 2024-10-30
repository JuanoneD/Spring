package com.example.demo.impl;

import org.springframework.http.ResponseEntity;

import com.example.demo.services.LoginService;

public class Examplo implements LoginService{

    @Override
    public Integer login(String user, String password) {
        if(user.equals("admin") && password.equals("admin")){
            return 1;
        }
        return -1;
    }
    
}
