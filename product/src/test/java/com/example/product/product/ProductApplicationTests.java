package com.example.product.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.product.product.entities.Product;
import com.example.product.product.repos.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class ProductApplicationTests {
	@Autowired
	ProductRepository repository;

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

	@Test
		public void testFindByName(){
		List<Product> products = repository.findByName("Sausages");
		products.forEach(p->System.out.println(p.getPrice()));
	}

	@Test
	public void testFindByNameOrPrice(){
		List<Product> products = repository.findByNameOrPrice("Sausages", 936d);
		products.forEach(p->System.out.println(p.getPrice()));
	}

	@Test
	public void testFindByNameOrPriceGreaterThan(){
		List<Product> products = repository.findByNameOrPriceGreaterThan("Sausages", 600d);
		products.forEach(p->System.out.println(p.getId() + " " + p.getPrice()));
	}

	@Test
	public void testFindByDescriptionContains(){
		List<Product> products = repository.findByDescriptionContains("Apple");
		products.forEach(p->System.out.println(p.getName() + " " + p.getPrice()));
	}

	@Test
	public void testFindByPriceBetween(){
		List<Product> products = repository.findByPriceBetween(500d, 1600d);
		products.forEach(p->System.out.println(p.getName() + " " + p.getPrice()));
	}

	@Test
	public void testFindByDescriptionLike(){
		List<Product> products = repository.findByDescriptionLike("%Apple%");
		products.forEach(p->System.out.println(p.getName() + " " + p.getPrice()));
	}

	@Test
	public void testFindAllPaging(){
		Pageable pageable = PageRequest.of(0, 5);
		Page<Product> results = repository.findAll(pageable);
		results.forEach(p->System.out.println(p.getName()));
	}

//	@Test
//	public void testFindAllSorting(){
//		repository.findAll(new Sort("name")).forEach(p->System.out.println(p.getName()));
//	}

	@Test
	public void testFindAllSorting(){
		repository.findAll(new Sort(Sort.Direction.DESC,"name")).forEach(p->System.out.println(p.getName()));
	}
}