package com.example.demo.services;

public interface LoginService {
    Integer login(String user,String password);
    boolean checkEmail(String email);
    boolean checkPassword(String password);
    
}
