package com.retail.store.repository;

import com.retail.store.entity.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItem, Long> {
}
