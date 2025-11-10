package com.retail.store.Dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    // Reference to the Category
    private Long categoryId;
    private String categoryName; // optional, useful for displaying in UI
}
