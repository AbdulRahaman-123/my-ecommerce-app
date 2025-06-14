package com.retailx.CommerceEngine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(unique = true)
    private String productName;

    private Long availableQuantity;

    @Column(nullable = false)
    public String description;

    @Column(name = "unitPrice", nullable = false)
    private Double price;

    //A single product can have multiple reviews
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reviews> reviews = new ArrayList<>();

}
