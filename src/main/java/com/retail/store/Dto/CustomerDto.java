package com.retail.store.Dto;


import lombok.Data;

@Data
public class CustomerDto {
    private String name;

    private String email;

    private Long categoryId;
}
