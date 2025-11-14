package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity

public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalCustomers;
    private int totalProducts;
    private double totalSales;
    private double totalProfit;

    private LocalDateTime lastUpdated;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
