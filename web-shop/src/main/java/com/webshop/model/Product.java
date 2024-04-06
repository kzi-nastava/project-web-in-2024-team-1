package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String Name;

    @Column(name = "description")
    private String Description;

    @Column(name = "image_path")
    private String ImagePath;

    @Column(name = "category")
    private Category category;

    @Column(name = "price")
    private Double Price;

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_type",nullable = false)
    private SalesType salesType;


    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private LocalDate ReleaseDate;

    @Column(name = "customer_review")
    private Boolean CustomerReview;

    @Column(name = "seller_review")
    private Boolean SellerReview;

    @Column(name = "is_sold")
    private Boolean isSold;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
