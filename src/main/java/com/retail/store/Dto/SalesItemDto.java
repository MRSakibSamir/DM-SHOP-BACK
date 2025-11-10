package com.retail.store.Dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesItemDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    // Reference to Category
    private Long categoryId;
    private String categoryName; // optional, for UI/API display

    // Reference to Sales
    private Long salesId;
    private String salesName; // optional, for UI/API display
}
