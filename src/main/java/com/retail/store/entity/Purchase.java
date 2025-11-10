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
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Name of purchased item
    private String description; // Optional description
    private BigDecimal price;   // Price at purchase
    private Integer quantity;   // Quantity purchased

    @ManyToOne
    @JoinColumn(name = "category_id") // foreign key to Category
    private Category category;
}
