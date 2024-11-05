package com.example.demo.services;

import com.example.demo.model.User;

public interface SecurityService {
    String encode(String password);
    boolean checkEncode(String password,String encodePassword);
    String generateToken(User user);
    String GetToken(String Token);
}
