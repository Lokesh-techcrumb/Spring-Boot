package com.example.product.product.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.product.product.entities.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{

    
} 