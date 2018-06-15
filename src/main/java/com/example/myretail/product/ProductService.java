package com.example.myretail.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.myretail.exception.ProductNotFoundException;

@Component
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductRestClient productRestClient;
		
	public Product findById(long id) {
		
		Product product = productRepository.findById(id);
		
		if (product == null) {
			throw new ProductNotFoundException();
		}

		String productName = productRestClient.getProductName(id);
		product.setName(productName);
		
		return product;
	}	
	
}
