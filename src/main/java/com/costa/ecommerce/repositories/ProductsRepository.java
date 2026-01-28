package com.costa.ecommerce.repositories;

import com.costa.ecommerce.domain.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductsRepository extends JpaRepository<Products, UUID> {
}
