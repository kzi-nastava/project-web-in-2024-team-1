package com.webshop.model;

import jakarta.persistence.*;

import java.beans.ConstructorProperties;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Offer implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double priceOffer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_offer_id", referencedColumnName = "id")
    private Account customerOfferId;


    public Offer()
    {
        this(1.0);
    }

    public Offer(Double priceOffer)
    {
        this.priceOffer = priceOffer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
    }


    public Account getAccount() {
        return customerOfferId;
    }

    public void setAccount(Account account) {
        this.customerOfferId = account;
    }

    @Override
    public String toString()
    {
        return "Offer{" +
                "id=" + id +
                ", PriceOffer=" + priceOffer +
                '}';
    }
}
