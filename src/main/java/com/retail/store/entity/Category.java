package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // Self-referencing parent category (optional)
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

//    // Optional: list of child categories
//    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Category> subCategories = new ArrayList<>();

    // Optional: products in this category
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products> products = new ArrayList<>();
}
