package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repositories.UserRepo;
import com.example.demo.services.LoginService;

public class UserImpl implements LoginService{

    @Autowired
    UserRepo userRepo;

    @Override
    public Integer login(String user, String password) {
        if(user.equals("admin") && password.equals("admin")){
            return 1;
        }
        return -1;
    }

    @Override
    public boolean checkEmail(String email) {
        if(email.length()<4)
            return false;
            
        if(userRepo.findByEmail(email).isEmpty())
            return false;
        
        try {
            email.split("@")[1].charAt(0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkPassword(String password) {
        if(password.length()<8)
            return false;
        
        Boolean letterUp=false;
        Boolean number=false;
        Boolean letter=false;

        for (char c : password.toCharArray()) {
            if(Character.isDigit(c))
                number = true;
            if(Character.isLowerCase(c))
                letter = true;
            if(Character.isUpperCase(c))
                letterUp = true;
        }

        return number && letter && letterUp;
    }
    
}
