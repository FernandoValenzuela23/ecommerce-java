package com.fva.microservices.product_service.model.dto;

public record ProductRequestDto(String name, String description, java.math.BigDecimal price) {

}
