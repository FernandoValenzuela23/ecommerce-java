package com.fva.microservices.inventory.service;

import org.springframework.stereotype.Service;

import com.fva.microservices.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRepository _inventoryRepository;
	
	public boolean isInStock(String skuCode, Integer quantity)
	{
		return _inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
	}

}
