package com.fva.microservices.order_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fva.microservices.order_service.dto.OrderRequestDto;
import com.fva.microservices.order_service.dto.OrderResponseDto;
import com.fva.microservices.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService _orderService;
		
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrderResponseDto placeOrder(@RequestBody OrderRequestDto orderRequest) {
		return _orderService.placeOrder(orderRequest);		 
	}
}
