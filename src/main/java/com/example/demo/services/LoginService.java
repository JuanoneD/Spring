package com.example.demo.services;

import com.example.demo.model.User;

public interface LoginService {
    User login(String user,String password);
    boolean checkUser(String user);
    boolean checkEmail(String email);
    boolean checkPassword(String password);
    boolean createAccount(String user,String email,String password);
    User changePassword(User currUser,String newPassword,String newPassword1);
}
