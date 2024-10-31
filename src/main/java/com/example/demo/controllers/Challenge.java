package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.CitiesDto;
import com.example.demo.dto.Collazt;
import com.example.demo.dto.Cu;
import com.example.demo.dto.Exp;
import com.example.demo.dto.Reverser;
import com.example.demo.dto.UserInfo;
import com.example.demo.model.Cities;
import com.example.demo.repositories.CitiesRepo;
import com.example.demo.services.CitiesService;

import java.util.*;




@RestController
@CrossOrigin(origins = {"http://localhost:5257/"})
public class Challenge {

    @Autowired
    CitiesRepo cityRepo;
    @Autowired
    CitiesService cityServ;

    @GetMapping("/reverse/{word}")
    public ResponseEntity<Reverser> chanllege1(@PathVariable String word){
        String reverseWord="";
        Boolean palidrome = true;
        for(int i=0;i<word.length();i++){
            if(word.charAt(i) != word.charAt(word.length() - (i+1))){
                palidrome = false;
            }
            reverseWord += word.charAt(word.length() - (i+1));
        }
        return ResponseEntity.ok(new Reverser(reverseWord, palidrome));
    }

    @GetMapping("/imaexp")
    public ResponseEntity<Exp> Challenge2(Integer A,Integer b){

        Double re = A * Math.sin(b);
        Double im = A * Math.cos(b);

        return ResponseEntity.ok(new Exp(re, im));
    }

    @GetMapping("/collatz")
    public ResponseEntity<Collazt> Challenge3(Integer step,Integer current){
        if(step<0 || current<0){
            return ResponseEntity.badRequest().build();
        }

        for(int i=0;i<step;i++){
            current = (current&1)==1?3*current+1:current/2;
        }

        return ResponseEntity.ok(new Collazt(current));
    }
    
    @GetMapping("/curitiba")
    public ResponseEntity<Cu> Challenge4(Integer cep, String cpf){
        try {
            RestTemplate rest = new RestTemplate();
            String result = rest.getForObject("https://viacep.com.br/ws/01001000/json/", String.class);
            System.out.println(result);
            
        } catch (Exception e) {
            System.out.println("CEP INVERIFICAVEL!");
        }

        int sum = 0;
        for(int i=0;i<10;i++){
            sum += ((int)cpf.charAt(i) - 48)*(i+1);
        }
        int d1 = sum%11==10?0:sum%11;

        if(cpf.charAt(9) != String.valueOf(d1).charAt(0)){
            return ResponseEntity.ok(new Cu(false, "CPF INVALIDO!"));
        }
        
        sum = 0;
        for(int i=0;i<10;i++){
            sum += ((int)cpf.charAt(i) - 48)*i;
        }

        int d2 = sum%11==10?0:sum%11;

        if(cpf.charAt(10) != String.valueOf(d2).charAt(0)){
            return ResponseEntity.ok(new Cu(false, "CPF INVALIDO!"));
        }

        System.out.println("AAA");

        return ResponseEntity.ok(new Cu(true, "CPF VALIDO"));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CitiesDto>> Challenge5(){
        return new ResponseEntity<>(cityServ.toVehicleDto(cityRepo.findAll()),HttpStatus.OK);
    }

    @GetMapping("/cities/{query}")
    public ResponseEntity<List<CitiesDto>> Challenge5v2(@PathVariable String query){
        return new ResponseEntity<>(cityServ.toVehicleDto(cityRepo.findByCity(query)),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> Challenge6(@RequestBody UserInfo data){
        
    }
}
