package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserInfo;
import com.example.demo.services.LoginService;


@RestController
@RequestMapping("/loginAA")
public class LoginController {
    @Autowired
    LoginService service;
    
    @PostMapping()
    public ResponseEntity<String> login(@RequestBody UserInfo data){
        if(service.login(data.username(), data.password()) != null){
            return ResponseEntity.ok("Sucesso!");
        }
        return ResponseEntity
            .status(404)
            .build();
    }
}
