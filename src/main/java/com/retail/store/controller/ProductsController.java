package com.retail.store.controller;

import com.retail.store.Dto.ProductsResponseDto;
import com.retail.store.entity.FileData;
import com.retail.store.entity.Products;
import com.retail.store.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    // Mapping entity to DTO
    private ProductsResponseDto mapToDTO(Products product) {
        List<String> images = product.getImages() != null
                ? product.getImages().stream().map(FileData::getImageUrl).toList()
                : List.of();

        return ProductsResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .images(images)
                .build();
    }

    // Create product
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductsResponseDto> createProduct(
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart("product") Products product) throws IOException {

        Products saved = productsService.saveProduct(product, images);
        return ResponseEntity.ok(mapToDTO(saved));
    }

    // Update product
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductsResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart("product") Products product) throws IOException {

        Products updated = productsService.updateProduct(id, product, images);
        return ResponseEntity.ok(mapToDTO(updated));
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product deleted successfully!");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductsResponseDto>> getAllProducts() {
        List<ProductsResponseDto> dtos = productsService.getAllProducts()
                .stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductsResponseDto> getProductById(@PathVariable Long id) {
        Products product = productsService.getProductById(id);
        return ResponseEntity.ok(mapToDTO(product));
    }
}
