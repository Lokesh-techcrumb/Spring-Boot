package com.example.hibernateInheritance.repos;

import com.example.hibernateInheritance.entities.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
