package com.fva.microservices.order_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fva.microservices.order_service.client.InventoryClient;
import com.fva.microservices.order_service.dto.OrderRequestDto;
import com.fva.microservices.order_service.dto.OrderResponseDto;
import com.fva.microservices.order_service.model.Order;
import com.fva.microservices.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository _orderRepository;
	private final InventoryClient _inventoryClient;
	
	public OrderResponseDto placeOrder(OrderRequestDto orderRequest) {
		
		var isproductInStock = _inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());	
		
		
		if( isproductInStock) {
		
			Order order = new Order();
			order.setOrderNumber(UUID.randomUUID().toString());
			order.setSkuCode(orderRequest.skuCode());
			order.setQuantity(orderRequest.quantity());
			order.setPrice(orderRequest.price());
			
			Order result = _orderRepository.save(order);
			
			return new OrderResponseDto(result.getId(), result.getOrderNumber(), result.getSkuCode(), result.getPrice(), result.getQuantity());
		
		}
		else
		{
			throw new RuntimeException("Product " + orderRequest.skuCode() + " is not available.");
		}
		
		
	}

}
