package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;    // Customer contact
    private String phone;
    private String address;

    // Optional: favorite product
    @ManyToOne
    @JoinColumn(name = "favorite_product_id")
    private Products favoriteProduct;

    // List of orders placed by this customer
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SalesOrder> salesOrders = new ArrayList<>();

    // Helper methods for managing orders
    public void addSalesOrder(SalesOrder order) {
        salesOrders.add(order);
        order.setCustomer(this);
    }

    public void removeSalesOrder(SalesOrder order) {
        salesOrders.remove(order);
        order.setCustomer(null);
    }
}
