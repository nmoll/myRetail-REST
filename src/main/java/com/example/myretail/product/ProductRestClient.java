package com.example.myretail.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.myretail.exception.ProductNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProductRestClient {
	
	private static final String EXCLUDE_PROPERTIES = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${api.product.baseurl}")
	private String productBaseUrl;
	

	@SuppressWarnings("unchecked")
	public String getProductName(long id) throws ProductNotFoundException {
		String url = productBaseUrl + id + EXCLUDE_PROPERTIES;

		String productName;
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			Map<String, Object> responseDetails = objectMapper.readValue(response.getBody(), Map.class);
			Map<String, Object> productDetails = (Map<String, Object>) responseDetails.get("product");
			Map<String, Object> itemDetails = (Map<String, Object>) productDetails.get("item");
			Map<String, Object> productDescription = (Map<String, Object>) itemDetails.get("product_description");
			productName = (String) productDescription.get("title");
		} catch (Exception e) {
			throw new ProductNotFoundException();
		}

		return productName;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}

	
}
