package com.costa.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductsResponseDTO(UUID id, String name, String description, double price, int stock, LocalDateTime createdAt) {
}
