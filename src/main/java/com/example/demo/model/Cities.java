package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String city;

    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    @Column
    private String state;


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }

    @Column
    private String country;

    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }

}
