package com.costa.ecommerce.repositories;

import com.costa.ecommerce.domain.Orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
