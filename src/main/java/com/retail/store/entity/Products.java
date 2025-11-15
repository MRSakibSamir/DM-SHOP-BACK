package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "products")

public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // One product can have multiple images (FileData)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileData> images = new ArrayList<>();

    // Helper methods
    public void addImage(FileData fileData) {
        images.add(fileData);
        fileData.setProduct(this);
    }

    public void removeImage(FileData fileData) {
        images.remove(fileData);
        fileData.setProduct(null);
    }
}
