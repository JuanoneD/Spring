package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Product;
import com.example.demo.repositories.ProductRepo;
import com.example.demo.services.ProductService;

public class ProductImp implements ProductService{
    
    @Autowired
    ProductRepo repo;

    @Override
    public boolean createProduct(String title, Float price) {
        var newProduct = new  Product();
        newProduct.setTitle(title);
        newProduct.setValue(price);
        repo.save(newProduct);
        return true;
    }
    
}
