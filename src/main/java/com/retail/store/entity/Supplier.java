package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Supplier name
    private String contactInfo; // Optional field for supplier contact details
    private String description; // Optional description

    // Optional: Many suppliers can provide products in a category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
