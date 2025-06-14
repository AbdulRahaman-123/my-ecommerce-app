package com.retailx.CommerceEngine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private String mode;

    private String cardNumber;

    private String cvv;

    private String expirationDate;

    private String confirmationCode;



    @PrePersist
    public void generateConfirmationCode() {
        this.confirmationCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

}
