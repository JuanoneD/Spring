package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.LoginService;
import com.example.demo.services.SecurityService;

public class EncripyUser implements LoginService{
    
    @Autowired
    SecurityService secS;
    
    @Autowired
    UserRepo repo;

    @Override
    public User login(String user, String password) {
        var currUser = repo.findByUsername(user);

        if(currUser.isEmpty())
            currUser = repo.findByEmail(user);

        if(currUser.isEmpty())
            return null;
        
        if(!secS.checkEncode(password, currUser.get(0).getPassword()))
            return null;

        return currUser.get(0);
    }

    @Override
    public boolean checkUser(String user) {
        if(!repo.findByUsername(user).isEmpty())
            return false;
        
        return true;
    }

    @Override
    public boolean checkEmail(String email) {
        if(email.length()<4)
            return false;
            
        if(!repo.findByEmail(email).isEmpty())
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
        newUser.setUsername(user);
        newUser.setPassword(secS.encode(password));
        repo.save(newUser);
        return true;
    }

    @Override
    public User changePassword(User currUser, String newPassword, String newPassword1) {
        if(currUser == null)
            return null;
        if (!newPassword.equals(newPassword1))
            return null;
        if(!checkPassword(newPassword))
            return null;

        currUser.setPassword(secS.encode(newPassword));

        return repo.save(currUser);
    }
    
}
