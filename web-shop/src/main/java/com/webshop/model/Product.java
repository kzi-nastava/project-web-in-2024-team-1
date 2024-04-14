package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Name;

    @Column
    private String Description;

    @Column
    private String ImagePath;

    @Column
    private Double Price;

    @Enumerated(EnumType.STRING)
    @Column
    private SalesType salesType;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate ReleaseDate;

    @ManyToOne
    @JoinColumn
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "OFFERS",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id")
    )
    private List<Offer> offers;

    @Column
    private Boolean CustomerReview;

    @Column
    private Boolean SellerReview;

    @Column
    private Boolean isSold;

    @Enumerated(EnumType.STRING)
    @Column
    private ProductType productType;

    public Product() {

        this(1L,"","","default.jpg",1.0,SalesType.FIXED_PRICE,LocalDate.MIN,null,new ArrayList<>(),false,false,false,ProductType.FOR_SALE);
    }

    public Product(Long id, String name, String description, String imagePath,
                   Double price, SalesType salesType, LocalDate releaseDate, Category category,
                   List<Offer> offers,Boolean customerReview, Boolean sellerReview, Boolean isSold,ProductType productType) {
        this.id = id;
        this.Name = name;
        this.Description = description;
        this.ImagePath = imagePath;
        this.Price = price;
        this.salesType = salesType;
        this.ReleaseDate = releaseDate;
        this.category = category;
        this.offers=offers;
        this.CustomerReview = customerReview;
        this.SellerReview = sellerReview;
        this.isSold = isSold;
        this.productType=productType;
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
                ", ProductType=" + productType +
                '}';
    }
}
