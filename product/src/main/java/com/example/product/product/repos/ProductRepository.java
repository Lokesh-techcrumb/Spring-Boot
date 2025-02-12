package com.example.product.product.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.product.product.entities.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer>{

    List<Product> findByName(String name);

    List<Product> findByNameOrPrice(String name, Double price);

    List<Product> findByNameOrPriceGreaterThan(String name, Double price);

    List<Product> findByDescriptionContains(String desc);
}