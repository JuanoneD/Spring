package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SumResult;

@RestController
@RequestMapping("/test")
public class TextController {
    @GetMapping
    public Integer test(Integer a,Integer b){
        return a+b;
    }
    @GetMapping("/{a}")
    public SumResult test2(@PathVariable Integer a,Integer b){
        if(b==null){
            b=2;
        }
        return new SumResult(a*b, (a*b)%2==0);
    }
}
