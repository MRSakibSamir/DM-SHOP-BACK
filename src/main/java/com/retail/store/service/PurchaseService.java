package com.retail.store.service;

import com.retail.store.entity.Purchase;
import com.retail.store.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Purchase updatePurchase(Long id, Purchase updatedPurchase) {
        return purchaseRepository.findById(id)
                .map(existing -> {
                    existing.setPrice(updatedPurchase.getPrice());
                    existing.setName(updatedPurchase.getName());
                    existing.setId(updatedPurchase.getId());
                    return purchaseRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Purchase not found with id: " + id));
    }

    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }
}
