package com.retail.store.Dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

//    // Instead of embedding full parentCategory, we can just use its ID
//    private Long parentCategoryId;
}
