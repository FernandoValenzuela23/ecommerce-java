package com.fva.microservices.product_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fva.microservices.product_service.model.Product;
import com.fva.microservices.product_service.model.dto.ProductRequestDto;
import com.fva.microservices.product_service.model.dto.ProductResponseDto;
import com.fva.microservices.product_service.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService _productService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProductResponseDto createProduct(@RequestBody ProductRequestDto product)
	{
		return _productService.createProduct(product);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProductResponseDto> getAllProducts()
	{
		return _productService.getAllProducts();
	}
	
}
