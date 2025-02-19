package com.example.HibernateMapping.repos;

import com.example.HibernateMapping.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
