package com.costa.ecommerce.domain.OrderItems;

import com.costa.ecommerce.domain.Orders.Orders;
import com.costa.ecommerce.domain.products.Products;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "order_items")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItems {
    @Id
    @GeneratedValue
    private UUID id;

    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
}
