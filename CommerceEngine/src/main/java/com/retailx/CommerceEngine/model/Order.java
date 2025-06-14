package com.retailx.CommerceEngine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long orderId;

    private LocalDateTime createdAt;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDate promisedDeliveryDate;

    private Double subTotal;
    private Double tax;
    private Double shippingCharges;
    private Double total;   //Sum of all

    @ManyToOne
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private CustomerAddress customerAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", unique = true)
    private Payment payment;
}
