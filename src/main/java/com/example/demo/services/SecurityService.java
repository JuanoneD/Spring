package com.example.demo.services;


public interface SecurityService {
    String encode(String password);
    boolean checkEncode(String password,String encodePassword);
}
