package com.example.employee.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.employee.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long>{

    
}