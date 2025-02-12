package com.example.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.employee.entities.Employee;
import com.example.employee.repos.EmployeeRepository;

@SpringBootTest
class EmployeeApplicationTests {

	@Autowired
	EmployeeRepository repo;

	@Test
	public void testCreateEmployee(){
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("lokesh");
		repo.save(emp);
	}
}
