package com.retail.store.service;

import com.retail.store.entity.Inventory;
import com.retail.store.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory updatedInventory) {
        return inventoryRepository.findById(id)
                .map(existing -> {
                    existing.setPrice(updatedInventory.getPrice());
                    existing.setQuantity(updatedInventory.getQuantity());
                    existing.setName(updatedInventory.getName());
                    existing.setId(updatedInventory.getId());
                    existing.setDescription(updatedInventory.getDescription());
                    return inventoryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Inventory record not found with id: " + id));
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
