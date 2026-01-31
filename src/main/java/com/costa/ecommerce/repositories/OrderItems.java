package com.costa.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItems extends JpaRepository<OrderItems, UUID> {
}
