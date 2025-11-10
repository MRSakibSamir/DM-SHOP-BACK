package com.retail.store.controller;

import com.retail.store.entity.PurchaseItem;
import com.retail.store.service.PurchaseItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-items")
public class PurchaseItemController {

    private final PurchaseItemService purchaseItemService;

    public PurchaseItemController(PurchaseItemService purchaseItemService) {
        this.purchaseItemService = purchaseItemService;
    }

    @GetMapping
    public List<PurchaseItem> getAllPurchaseItems() {
        return purchaseItemService.getAllPurchaseItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseItem> getPurchaseItemById(@PathVariable Long id) {
        return purchaseItemService.getPurchaseItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PurchaseItem createPurchaseItem(@RequestBody PurchaseItem item) {
        return purchaseItemService.createPurchaseItem(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseItem> updatePurchaseItem(@PathVariable Long id, @RequestBody PurchaseItem item) {
        try {
            PurchaseItem updated = purchaseItemService.updatePurchaseItem(id, item);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseItem(@PathVariable Long id) {
        purchaseItemService.deletePurchaseItem(id);
        return ResponseEntity.noContent().build();
    }
}
