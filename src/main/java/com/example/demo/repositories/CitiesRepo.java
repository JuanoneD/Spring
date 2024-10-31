package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cities;

@Repository
public interface CitiesRepo extends JpaRepository<Cities,Long>{
    List<Cities> findByCity(String City);
} 
