package com.example.myretail.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.myretail.exception.BadRequestException;
import com.example.myretail.exception.ProductNotFoundException;

@Component
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductRestClient productRestClient;
		
	public Product findById(Long id) throws ProductNotFoundException {
		
		Product product = productRepository.findById(id);
		
		if (product == null) {
			throw new ProductNotFoundException(String.format("Product not found for Id %d", id));
		}

		String productName = productRestClient.getProductName(id);
		product.setName(productName);
		
		return product;
	}	
	
	public Product update(Long id, Product unmanagedProduct) {
		if (id != unmanagedProduct.getId()) {
			throw new BadRequestException(String.format("Path Product Id: %d does not match Product Id from request body: %d", id, unmanagedProduct.getId()));
		}
		
		if (productRepository.findById(id) == null) {
			throw new ProductNotFoundException(String.format("Product not found for Id %d", id));
		}
		
		return productRepository.save(unmanagedProduct);
	}
	
}
