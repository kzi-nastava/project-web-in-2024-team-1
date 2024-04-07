package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



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

    @Column(name = "price")
    private Double Price;

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_type",nullable = false)
    private SalesType salesType;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private LocalDate ReleaseDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Offer> offers;

    @Column(name = "customer_review")
    private Boolean CustomerReview;

    @Column(name = "seller_review")
    private Boolean SellerReview;

    @Column(name = "is_sold")
    private Boolean isSold;


    public Product() {

        this(1L,"","","default.jpg",1.0,SalesType.FIXED_PRICE,LocalDate.MIN,new Category(),new ArrayList<>(),false,false,false);
    }

    public Product(Long id, String name, String description, String imagePath,
                   Double price, SalesType salesType, LocalDate releaseDate, Category category,
                   List<Offer> offers, Boolean customerReview, Boolean sellerReview, Boolean isSold) {
        this.id = id;
        this.Name = name;
        this.Description = description;
        this.ImagePath = imagePath;
        this.Price = price;
        this.salesType = salesType;
        this.ReleaseDate = releaseDate;
        this.category = category;
        this.offers = offers;
        this.CustomerReview = customerReview;
        this.SellerReview = sellerReview;
        this.isSold = isSold;
    }

    public Product(String name, String description, Double price,
                   SalesType salesType, LocalDate releaseDate, Category category, String imagePath) {
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.salesType = salesType;
        this.ReleaseDate = releaseDate;
        this.category = category;
        this.ImagePath = imagePath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        this.ImagePath = imagePath;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }

    public SalesType getSalesType() {
        return salesType;
    }

    public void setSalesType(SalesType salesType) {
        this.salesType = salesType;
    }

    public LocalDate getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.ReleaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Boolean getCustomerReview() {
        return CustomerReview;
    }

    public void setCustomerReview(Boolean customerReview) {
        this.CustomerReview = customerReview;
    }

    public Boolean getSellerReview() {
        return SellerReview;
    }

    public void setSellerReview(Boolean sellerReview) {
        this.SellerReview = sellerReview;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        this.isSold = sold;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", ImagePath='" + ImagePath + '\'' +
                ", Price=" + Price +
                ", salesType=" + salesType +
                ", ReleaseDate=" + ReleaseDate +
                ", category=" + category +
                ", offers=" + offers +
                ", CustomerReview=" + CustomerReview +
                ", SellerReview=" + SellerReview +
                ", isSold=" + isSold +
                '}';
    }
}
