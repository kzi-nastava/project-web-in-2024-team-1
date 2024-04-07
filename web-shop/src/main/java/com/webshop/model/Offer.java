package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price_offer")
    private Double PriceOffer;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public Offer() {
        this(1L,1.0,Role.CUSTOMER);
    }

    public Offer(Long id, Double priceOffer, Role role) {
        this.id = id;
        this.PriceOffer = priceOffer;
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getPriceOffer() {
        return PriceOffer;
    }

    public void setPriceOffer(Double priceOffer) {
        this.PriceOffer = priceOffer;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
