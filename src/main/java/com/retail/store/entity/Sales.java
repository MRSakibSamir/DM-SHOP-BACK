package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;

    private Integer quantity;
    private BigDecimal price;
    private BigDecimal lineTotal;

    @PrePersist
    @PreUpdate
    public void calculateLineTotal() {
        if (price != null && quantity != null) {
            this.lineTotal = price.multiply(BigDecimal.valueOf(quantity));
        } else {
            this.lineTotal = BigDecimal.ZERO;
        }
    }
}
