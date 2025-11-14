package com.retail.store.repository;

import com.retail.store.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    @Query("SELECT SUM(s.totalAmount) FROM Sales s")
    Double sumTotalSales();

    @Query("SELECT SUM(s.totalAmount) FROM Sales s WHERE DATE(s.salesDate) = :date")
    Double sumTodaySales(LocalDate date);

    @Query("SELECT COUNT(s) FROM Sales s WHERE DATE(s.salesDate) = :date")
    Long countTodaySales(LocalDate date);
}
