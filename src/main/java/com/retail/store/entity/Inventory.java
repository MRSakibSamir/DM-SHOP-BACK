package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // Inventory item name
    private String description;     // Optional description
    private BigDecimal price;       // Price per item
    private Integer quantity;       // Available quantity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id") // Foreign key to Category
    private Category category;

    // Optional: link to a product (if inventory is tied to a specific product)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;

    // Optional: helper method to adjust stock
    public void adjustQuantity(int delta) {
        if (this.quantity == null) this.quantity = 0;
        this.quantity += delta;
        if (this.quantity < 0) this.quantity = 0; // avoid negative stock
    }
}
