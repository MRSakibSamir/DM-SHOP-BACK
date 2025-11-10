
package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class FileData {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String imageUrl;

        private String fileName;
        private String fileType;
        private byte[] data;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Products product;
    }
