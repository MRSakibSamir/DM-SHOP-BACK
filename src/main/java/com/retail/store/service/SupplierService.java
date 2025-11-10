package com.retail.store.service;

import com.retail.store.entity.Supplier;
import com.retail.store.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        return supplierRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedSupplier.getName());
                    existing.setName(updatedSupplier.getName());
                    existing.setId(updatedSupplier.getId());
                    existing.setCategory(updatedSupplier.getCategory());
                    return supplierRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
