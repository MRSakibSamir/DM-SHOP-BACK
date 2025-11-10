package com.retail.store.service;

import com.retail.store.entity.PurchaseItem;
import com.retail.store.repository.PurchaseItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;

    public PurchaseItemService(PurchaseItemRepository purchaseItemRepository) {
        this.purchaseItemRepository = purchaseItemRepository;
    }

    public List<PurchaseItem> getAllPurchaseItems() {
        return purchaseItemRepository.findAll();
    }

    public Optional<PurchaseItem> getPurchaseItemById(Long id) {
        return purchaseItemRepository.findById(id);
    }

    public PurchaseItem createPurchaseItem(PurchaseItem purchaseItem) {
        return purchaseItemRepository.save(purchaseItem);
    }

    public PurchaseItem updatePurchaseItem(Long id, PurchaseItem updatedItem) {
        return purchaseItemRepository.findById(id)
                .map(existing -> {
                    existing.setPrice(updatedItem.getPrice());
                    existing.setQuantity(updatedItem.getQuantity());
                    return purchaseItemRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("PurchaseItem not found with id: " + id));
    }

    public void deletePurchaseItem(Long id) {
        purchaseItemRepository.deleteById(id);
    }
}
