package com.costa.ecommerce.controllers;

import com.costa.ecommerce.domain.products.Products;
import com.costa.ecommerce.dto.ProductsRequestDTO;
import com.costa.ecommerce.dto.ProductsResponseDTO;
import com.costa.ecommerce.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @PostMapping("/register")
    public ResponseEntity<Products> createProducts(@RequestBody ProductsRequestDTO body) {
        Products product = this.productsService.createProduct(body);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductsResponseDTO>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<ProductsResponseDTO> allProducts = this.productsService.getProducts(page, size);
        return ResponseEntity.ok(allProducts);
    }
}
