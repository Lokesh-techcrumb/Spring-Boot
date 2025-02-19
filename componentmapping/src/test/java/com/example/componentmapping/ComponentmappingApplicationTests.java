package com.example.componentmapping;

import com.example.componentmapping.entities.Address;
import com.example.componentmapping.entities.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComponentmappingApplicationTests {

	@Test
	public void testCreate(){
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("lokesh");
		Address address = new Address();
		address.setCity("San Francisco");
		address.setCountry("USA");
		address.setState("CA");
		address.setStreetaddress("123 Main St");
		address.setZipCode("9410");
		employee.setAddress(address);
	}

}
