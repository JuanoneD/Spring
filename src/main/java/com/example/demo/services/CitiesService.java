package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Cities;
import com.example.demo.dto.CitiesDto;



public interface CitiesService {
    List<CitiesDto> toVehicleDto(List<Cities> list);
}
