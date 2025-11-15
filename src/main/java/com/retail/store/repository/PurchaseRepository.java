package com.retail.store.repository;

import com.retail.store.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT COALESCE(SUM(p.total), 0) FROM Purchase p")
    Double sumTotalPurchase();
}
