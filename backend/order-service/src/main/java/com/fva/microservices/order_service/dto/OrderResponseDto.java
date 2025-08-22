package com.fva.microservices.order_service.dto;

import java.math.BigDecimal;

public record OrderResponseDto(Long id,String orderNumber,String skuCode,BigDecimal price,Integer quantity) {}
