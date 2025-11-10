package com.retail.store.Dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    // Reference to Category
    private Long categoryId;
    private String categoryName; // optional, for display/UI

    // Reference to Purchase
    private Long purchaseId;
    private String purchaseName; // optional, for display/UI
}
