package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String imagePath;

    @Column
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column
    private SalesType salesType;

    @Temporal(TemporalType.DATE)
    @Column
    private Date releaseDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_OFFERS",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private List<Offer> offers;

    @Column
    private Boolean customerReview;

    @Column
    private Boolean sellerReview;

    @Column
    private Boolean isSold;

    @Enumerated(EnumType.STRING)
    @Column
    private ProductType productType;


    public Product() {
        this("", "", 1.0, SalesType.FIXED_PRICE, new Date(), "");
    }

    public Product(String name, String description, Double price,
                   SalesType salesType, Date releaseDate, String imagePath)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.salesType = salesType;
        this.releaseDate = releaseDate;
        this.imagePath = imagePath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SalesType getSalesType() {
        return salesType;
    }

    public void setSalesType(SalesType salesType) {
        this.salesType = salesType;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Boolean getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(Boolean customerReview) {
        this.customerReview = customerReview;
    }

    public Boolean getSellerReview() {
        return sellerReview;
    }

    public void setSellerReview(Boolean sellerReview) {
        this.sellerReview = sellerReview;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        this.isSold = sold;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", Description='" + description + '\'' +
                ", ImagePath='" + imagePath + '\'' +
                ", Price=" + price +
                ", salesType=" + salesType +
                ", ReleaseDate=" + releaseDate +
              //  ", category=" + category +
                ", offers=" + offers +
                ", CustomerReview=" + customerReview +
                ", SellerReview=" + sellerReview +
                ", isSold=" + isSold +
                ", ProductType=" + productType +
                '}';
    }
}
