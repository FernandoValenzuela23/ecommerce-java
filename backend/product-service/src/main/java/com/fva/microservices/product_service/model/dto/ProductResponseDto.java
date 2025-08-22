package com.fva.microservices.product_service.model.dto;

public record ProductResponseDto(String id, String name, String description, java.math.BigDecimal price) {

}
