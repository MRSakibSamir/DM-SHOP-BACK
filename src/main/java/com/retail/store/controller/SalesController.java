package com.retail.store.controller;

import com.retail.store.entity.Sales;
import com.retail.store.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<Sales> createSale(@RequestBody Sales sales) {
        Sales saved = salesRepository.save(sales);
        return ResponseEntity.ok(saved);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        return ResponseEntity.ok(salesRepository.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSaleById(@PathVariable Long id) {
        return salesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Sales> updateSale(@PathVariable Long id, @RequestBody Sales updated) {
        return salesRepository.findById(id)
                .map(sales -> {
                    // Update product details
//                    sales.setName(updated.getName());
//                    sales.setDescription(updated.getDescription());
                    sales.setPrice(updated.getPrice());
                    sales.setStock(updated.getStock());
                    sales.setCategory(updated.getCategory());

                    // Optionally update product reference if included
                    if (updated.getProduct() != null) {
                        sales.setProduct(updated.getProduct());
                    }

                    // lineTotal will be recalculated automatically via @PreUpdate
                    Sales saved = salesRepository.save(sales);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        if (salesRepository.existsById(id)) {
            salesRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
