package com.retailx.CommerceEngine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @Column(nullable = false, length = 1000)
    private String comment;
    @Column(nullable = false)
    private ReviewRating rating;
    private LocalDateTime reviewedAt;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @ManyToOne  //Who reviewed
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
