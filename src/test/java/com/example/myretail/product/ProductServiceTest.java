package com.example.myretail.product;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.myretail.exception.ProductNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private ProductRestClient productRestClient;
	
	private final long productId = 1l;
	private final String productName = "My Product";
	private final double price = 1.0;
	
	@Test
	public void findById() {	
		Product product = new Product(productId, productName, price);
		
		Mockito.when(productRepository.findById(productId)).thenReturn(product);
		Mockito.when(productRestClient.getProductName(productId)).thenReturn(productName);
		
		Product actual = productService.findById(productId);
		
		assertEquals(productId, actual.getId());
		assertEquals(productName, actual.getName());
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void findByIdNotFound() {
		Product product = null;
		
		Mockito.when(productRepository.findById(productId)).thenReturn(product);
		
		productService.findById(productId);
	}
	
}
