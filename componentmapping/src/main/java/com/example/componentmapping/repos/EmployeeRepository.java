package com.example.componentmapping.repos;

import com.example.componentmapping.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository  extends CrudRepository<Employee, Integer> {
}
