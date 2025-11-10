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
public class SalesItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Name of sold item
    private String description; // Optional description
    private BigDecimal price;   // Sale price
    private Integer quantity;   // Quantity sold

    // Many sales items can belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Optional: link to parent Sale
    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sales sales;
}
