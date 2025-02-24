package org.example.productservice.controller;

import org.example.productservice.model.Products;
import org.example.productservice.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class    ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping()
    public Products save(@RequestBody Products product) {
        return productRepository.save(product);
    }
}
