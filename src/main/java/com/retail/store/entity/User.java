package com.retail.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;      // User's full name
    private String email;     // User's email
    private String password;  // Hashed password
    private String role;      // User role (ADMIN, CUSTOMER, etc.)

    // Optional: if user should be linked to a category (like favorite category)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
