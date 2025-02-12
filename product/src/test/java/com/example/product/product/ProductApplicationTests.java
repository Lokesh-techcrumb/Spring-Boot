package com.example.product.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.product.product.entities.Product;
import com.example.product.product.repos.ProductRepository;

@SpringBootTest
class ProductApplicationTests {

	@Autowired
	ProductRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate(){
		Product product = new Product();
		product.setId(2);
		product.setName("iphone");
		product.setDesc("good");
		product.setPrice(10000d);

		repository.save(product);
	}

	@Test
	public void testRead(){
		if(repository.existsById(1)){
			Product product = repository.findById(1).get();
			assertNotNull(product);
			assertEquals("phone", product.getName());
			System.out.println(">>>>>>>>>>>>>>>>>>>" + product.getDesc() + ">>>>>>>>>>>>>>>>>>");
		}
	}

	@Test
	public void testUpdate(){
		if(repository.existsById(1)){
			Product product = repository.findById(1).get();
			product.setPrice(1500d);
			repository.save(product);
		}
	}

	@Test
	public void testDelete(){
		if(repository.existsById(1)){
			repository.deleteById(1);
		}
	}
}
