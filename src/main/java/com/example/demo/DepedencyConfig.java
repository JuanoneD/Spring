package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.impl.CitiesImp;
import com.example.demo.impl.UserImpl;
import com.example.demo.services.CitiesService;
import com.example.demo.services.LoginService;

@Configuration
@Scope("singleton")
public class DepedencyConfig {
    @Bean
    public LoginService loginService(){
        return new UserImpl();
    }
    @Bean
    public CitiesService citiesService(){
        return new CitiesImp();
    }
}
