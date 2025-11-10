package com.retail.store.repository;

import com.retail.store.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    // Optional: add custom queries if needed

    // Example: find all orders by a specific customer
    // List<SalesOrder> findByCustomerId(Long customerId);
}
