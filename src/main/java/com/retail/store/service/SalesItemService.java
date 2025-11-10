package com.retail.store.service;

import com.retail.store.entity.SalesItem;
import com.retail.store.repository.SalesItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesItemService {

    private final SalesItemRepository salesItemRepository;

    public SalesItemService(SalesItemRepository salesItemRepository) {
        this.salesItemRepository = salesItemRepository;
    }

    public List<SalesItem> getAllSalesItems() {
        return salesItemRepository.findAll();
    }

    public Optional<SalesItem> getSalesItemById(Long id) {
        return salesItemRepository.findById(id);
    }

    public SalesItem createSalesItem(SalesItem salesItem) {
        return salesItemRepository.save(salesItem);
    }

    public SalesItem updateSalesItem(Long id, SalesItem updatedItem) {
        return salesItemRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedItem.getName());
                    existing.setPrice(updatedItem.getPrice());
                    existing.setQuantity(updatedItem.getQuantity());
                    existing.setDescription(updatedItem.getDescription());
                    return salesItemRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("SalesItem not found with id: " + id));
    }

    public void deleteSalesItem(Long id) {
        salesItemRepository.deleteById(id);
    }
}
