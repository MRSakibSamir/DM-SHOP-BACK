package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sales_orders")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ------------------------
    // Customer who placed the order
    // ------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // ------------------------
    // List of sales items in this order
    // ------------------------
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Sales> salesItems = new ArrayList<>();

    // ------------------------
    // Order creation timestamp
    // ------------------------
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ------------------------
    // Convenience methods to manage bidirectional relationship
    // ------------------------
    public void addSale(Sales sale) {
        salesItems.add(sale);
        sale.setSalesOrder(this);
    }

    public void removeSale(Sales sale) {
        salesItems.remove(sale);
        sale.setSalesOrder(null);
    }

    // ------------------------
    // Automatically set creation timestamp
    // ------------------------
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
