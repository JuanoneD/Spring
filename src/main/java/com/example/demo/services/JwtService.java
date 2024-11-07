package com.example.demo.services;

public interface JwtService<T> {
    String get(T token);
    T validate(String jwt);
}