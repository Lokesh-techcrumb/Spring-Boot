package com.example.customer.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.customer.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    
}
