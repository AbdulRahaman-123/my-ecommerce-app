package com.retailx.CommerceEngine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    // Optional reverse mapping
    @OneToOne(mappedBy = "c_address")
    private Customer customer;

}