package com.retail.store.repository;

import com.retail.store.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("""
            SELECT COUNT(c)
            FROM Customer c
            WHERE MONTH(c.createdAt) = MONTH(:now)
              AND YEAR(c.createdAt) = YEAR(:now)
            """)
    long countNewCustomersThisMonth(LocalDateTime now);
}
