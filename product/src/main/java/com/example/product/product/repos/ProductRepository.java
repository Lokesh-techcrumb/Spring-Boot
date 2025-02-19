package com.example.product.product.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.product.product.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer>,PagingAndSortingRepository<Product,Integer> {

    List<Product> findByName(String name);

    List<Product> findByNameOrPrice(String name, Double price);

    List<Product> findByNameOrPriceGreaterThan(String name, Double price);

    List<Product> findByDescriptionContains(String desc);

    List<Product> findByPriceBetween(Double price1, Double price2);

    List<Product> findByDescriptionLike(String desc);
}