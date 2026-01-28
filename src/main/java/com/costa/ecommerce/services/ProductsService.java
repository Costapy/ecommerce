package com.costa.ecommerce.services;

import com.costa.ecommerce.domain.products.Products;
import com.costa.ecommerce.dto.ProductsRequestDTO;
import com.costa.ecommerce.dto.ProductsResponseDTO;
import com.costa.ecommerce.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public Products createProduct(ProductsRequestDTO data) {
        Products newProduct = new Products();
        newProduct.setName(data.name());
        newProduct.setDescription(data.description());
        newProduct.setPrice(data.price());
        newProduct.setStock(data.stock());
        this.productsRepository.save(newProduct);

        return newProduct;
    }

    public List<ProductsResponseDTO> getProducts(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Products> allProducts = this.productsRepository.findAll(pageable);
        return allProducts.map(products -> new ProductsResponseDTO(products.getId(), products.getName(), products.getDescription(), products.getPrice(), products.getStock(), products.getCreatedAt())).stream().toList();
    }
}
