package com.fva.microservices.product_service.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.fva.microservices.product_service.model.Product;
import com.fva.microservices.product_service.model.dto.ProductRequestDto;
import com.fva.microservices.product_service.model.dto.ProductResponseDto;
import com.fva.microservices.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository _productRepository;
	
	public ProductResponseDto createProduct(ProductRequestDto product)
	{
		Product newProduct = Product.builder()
				.name(product.name())
				.description(product.description())
				.price(product.price())
				.build();
		
		Product result = _productRepository.save(newProduct);
		
		ProductResponseDto response = new ProductResponseDto(result.getId(), result.getName(), result.getDescription(), result.getPrice());
		log.info("Product created successfully");
		return response;
		
	}
	
	public List<ProductResponseDto> getAllProducts()
	{
		return _productRepository.findAll()
				.stream()
				.map(p -> new ProductResponseDto(p.getId(), p.getName(), p.getDescription(), p.getPrice()))
				.toList();
	}

}
