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
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Name of purchased item
    private String description; // Optional description
    private BigDecimal price;   // Price at purchase
    private Integer quantity;   // Quantity purchased

    // Many purchase items can belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Optional: link to parent Purchase
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
}
