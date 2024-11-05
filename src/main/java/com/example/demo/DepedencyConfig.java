package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.impl.ByCryptImp;
import com.example.demo.impl.CitiesImp;
import com.example.demo.impl.EncripyUser;
import com.example.demo.impl.UserImpl;
import com.example.demo.services.CitiesService;
import com.example.demo.services.LoginService;
import com.example.demo.services.SecurityService;

@Configuration
@Scope("singleton")
public class DepedencyConfig {
    @Bean
    public LoginService loginService(){
        return new EncripyUser();
    }
    @Bean
    public CitiesService citiesService(){
        return new CitiesImp();
    }
    @Bean
    public SecurityService securityService(){
        return new ByCryptImp();
    }
}
