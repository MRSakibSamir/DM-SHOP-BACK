package com.retail.store.service;

import com.retail.store.entity.Category;
import com.retail.store.entity.FileData;
import com.retail.store.entity.Products;
import com.retail.store.repository.CategoryRepository;
import com.retail.store.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private final ProductsRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Value("${app.upload-dir:uploads}")
    private String uploadDir;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    public ProductsService(ProductsRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // ---------------- Public Methods ----------------

    // Save product
    public Products saveProduct(Products product, List<MultipartFile> imageFiles) throws IOException {
        setCategoryIfPresent(product);
        List<FileData> images = saveImages(imageFiles, product);
        product.setImages(images);
        return productRepository.save(product);
    }

    // Update product
    public Products updateProduct(Long id, Products product, List<MultipartFile> imageFiles) throws IOException {
        Products existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        setCategoryIfPresent(existing, product.getCategory());

        if (imageFiles != null && !imageFiles.isEmpty()) {
            deleteImagesFromDisk(existing);
            existing.setImages(saveImages(imageFiles, existing));
        }

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        deleteImagesFromDisk(product);
        productRepository.delete(product);
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // ---------------- Helper Methods ----------------

    private void setCategoryIfPresent(Products product) {
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + product.getCategory().getId()));
            product.setCategory(category);
        }
    }

    private void setCategoryIfPresent(Products existing, Category category) {
        if (category != null && category.getId() != null) {
            Category cat = categoryRepository.findById(category.getId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + category.getId()));
            existing.setCategory(cat);
        }
    }

    private List<FileData> saveImages(List<MultipartFile> files, Products product) throws IOException {
        if (files == null || files.isEmpty()) return List.of();

        File dir = new File(uploadDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Failed to create upload directory: " + uploadDir);
        }

        return files.stream().map(file -> {
            try {
                String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir, uniqueName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                String imageUrl = baseUrl.endsWith("/") ? baseUrl + "uploads/" + uniqueName : baseUrl + "/uploads/" + uniqueName;
                return FileData.builder().imageUrl(imageUrl).product(product).build();
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image: " + file.getOriginalFilename(), e);
            }
        }).collect(Collectors.toList());
    }

    private void deleteImagesFromDisk(Products product) {
        if (product.getImages() == null) return;
        for (FileData fileData : product.getImages()) {
            try {
                String imageUrl = fileData.getImageUrl();
                if (imageUrl != null && imageUrl.contains("/uploads/")) {
                    String filename = imageUrl.substring(imageUrl.lastIndexOf("/uploads/") + 9);
                    Files.deleteIfExists(Paths.get(uploadDir, filename));
                }
            } catch (Exception ignored) {}
        }
    }
}


//    private final ProductsRepository productsRepository;
//
//    public ProductsService(ProductsRepository productsRepository) {
//        this.productsRepository = productsRepository;
//    }
//
//    public List<Products> getAllProducts() {
//        return productsRepository.findAll();
//    }
//
//    public Optional<Products> getProductById(Long id) {
//        return productsRepository.findById(id);
//    }
//
//    public Products createProduct(Products product) {
//        return productsRepository.save(product);
//    }
//
//    public Products updateProduct(Long id, Products updatedProduct) {
//        return productsRepository.findById(id)
//                .map(existing -> {
//                    existing.setName(updatedProduct.getName());
//                    existing.setDescription(updatedProduct.getDescription());
//                    existing.setPrice(updatedProduct.getPrice());
//                    existing.setQuantity(updatedProduct.getQuantity());
//                    existing.setCategory(updatedProduct.getCategory());
//                    return productsRepository.save(existing);
//                })
//                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
//    }
//
//    public void deleteProduct(Long id) {
//        productsRepository.deleteById(id);
//    }