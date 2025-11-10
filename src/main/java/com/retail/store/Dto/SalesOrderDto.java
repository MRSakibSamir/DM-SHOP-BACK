package com.retail.store.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderDto {

    private Long id ;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotEmpty(message = "Sales items cannot be empty")
    private List<SalesItemDto> items;

    private List<Long> salesItemIds = new ArrayList<>();
    private LocalDateTime createdAt;
}
