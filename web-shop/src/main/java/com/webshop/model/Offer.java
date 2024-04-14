package com.webshop.model;

import jakarta.persistence.*;

import java.beans.ConstructorProperties;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double PriceOffer;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "product_id")
    //private Product product;


    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    public Offer() {
        this(1.0, Role.CUSTOMER);
    }

    public Offer(Double priceOffer, Role role) {
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

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", PriceOffer=" + PriceOffer +
                ", role=" + role +
                '}';
    }
}
