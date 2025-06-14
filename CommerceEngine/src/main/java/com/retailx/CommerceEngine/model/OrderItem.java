package com.retailx.CommerceEngine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    //Each order has multiple items
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private  Order order;

    //A single product can be in multiple orders
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer orderQuantity;
}
