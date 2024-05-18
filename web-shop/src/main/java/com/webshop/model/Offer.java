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
    private Double PriceOffer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    public Offer()
    {
        this(1.0);
    }

    public Offer(Double priceOffer)
    {
        this.PriceOffer = priceOffer;
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


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString()
    {
        String accountName = (account != null) ? account.getName() : "N/A";
        return "Offer{" +
                "id=" + id +
                ", PriceOffer=" + PriceOffer +
                ", accountName=" + accountName +
                '}';
    }
}
