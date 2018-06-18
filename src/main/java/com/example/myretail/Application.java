package com.example.myretail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.myretail.product.Product;
import com.example.myretail.product.ProductRepository;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
public class Application implements CommandLineRunner {
	
	@Autowired
	private ProductRepository productRepository;
	
    public static void main (String[] args) {
        SpringApplication.run(Application.class, args);   
    }

	@Override
	public void run(String... args) throws Exception {
        productRepository.save(new Product(13860428, "", 15.0));
	}
    
}
