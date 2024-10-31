package com.example.demo.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.CitiesDto;
import com.example.demo.model.Cities;
import com.example.demo.services.CitiesService;

public class CitiesImp implements CitiesService{

    @Override
    public List<CitiesDto> toVehicleDto(List<Cities> list) {
        return list.stream()
        .map(n->new CitiesDto(n.getCity(), n.getCountry(), n.getState()))
        .collect(Collectors.toList());
    }
}
