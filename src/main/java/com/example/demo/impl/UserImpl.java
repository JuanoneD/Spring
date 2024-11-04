package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.LoginService;

public class UserImpl implements LoginService{
    
    @Autowired
    UserRepo userRepo;
    
    @Override
    public User login(String user, String password) {
        var currUser = userRepo.findByUsername(user);
        if(user == null ||!currUser.get(0).getPassword().equals(password))
            return null;
        return currUser.get(0);
    }
    
    @Override
    public boolean checkUser(String user) {
        if(!userRepo.findByUsername(user).isEmpty())
            return false;
        
        return true;
    }

    @Override
    public boolean checkEmail(String email) {
        if(email.length()<4)
            return false;
            
        if(!userRepo.findByEmail(email).isEmpty())
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

    @Override
    public boolean createAccount(String user, String email, String password) {
        if(!checkEmail(email) || !checkPassword(password) || !checkUser(user))
            return false;
        
        var newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setUsername(user);

        userRepo.saveAndFlush(newUser);

        return true;
    }

    @Override
    public User changePassword(User currUser, String newPassword, String newPassword1) {
        if(currUser == null)
            return null;
        if (newPassword.equals(newPassword1))
            return null;
        if(!checkPassword(newPassword))
            return null;

        currUser.setPassword(newPassword);

        return userRepo.save(currUser);
    }
    
}
