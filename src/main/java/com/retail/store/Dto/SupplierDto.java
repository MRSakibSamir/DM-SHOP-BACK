package com.retail.store.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDto {

    private Long id;
    private String name;
    private String contactInfo;
    private String description;

    // Reference to Category
    private Long categoryId;
    private String categoryName; // optional, for UI/API display
}
