package com.example.HibernateMapping;

import com.example.HibernateMapping.entities.Customer;
import com.example.HibernateMapping.entities.PhoneNumber;
import com.example.HibernateMapping.repos.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class HibernateMappingApplicationTests {

	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setName("John Doe");

		PhoneNumber ph1= new PhoneNumber();
		ph1.setNumber("123456789");
		ph1.setType("home");

		PhoneNumber ph2= new PhoneNumber();
		ph2.setNumber("987654321");
		ph2.setType("work");

		customer.addPhoneNumber(ph1);
		customer.addPhoneNumber(ph2);

		customerRepository.save(customer);
	}

	@Test
	public void testLoadCustomer() {
		Customer customer = customerRepository.findAll(1L);
		System.out.println(customer.getName());

		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(number->System.out.println(number.getNumber()));
	}
}
