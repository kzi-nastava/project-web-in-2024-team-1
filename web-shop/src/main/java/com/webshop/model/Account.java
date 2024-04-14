package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String password;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate dateOfBirth;

    @Column
    private String imagePath;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private Role userRole;

    @Column
    private Boolean blocked;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ACCOUNT_PRODUCTS",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> productList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ACCOUNT_REVIEWS",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "review_id", referencedColumnName = "id")
    )
    private List<Review> reviewList;

    @Column
    private double averageRating;

    public Account() {
        this.id = 1L;
        this.name = "";
        this.lastName = "";
        this.username = "";
        this.email = "";
        this.phoneNumber = "";
        this.password = "";
        this.dateOfBirth = LocalDate.MIN;
        this.imagePath = "default.jpg";
        this.description = "bio";
        this.userRole = Role.CUSTOMER;
        this.blocked = false;
        this.productList = new ArrayList<>();
        this.reviewList = new ArrayList<>();
        this.averageRating = 1.0;
    }

    public Account(Long id, String name, String lastName, String username, String email, String phoneNumber, String password, LocalDate dateOfBirth, String imagePath, String description, Role userRole, Boolean blocked, List<Product> productList, List<Review> reviewList, double averageRating) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.imagePath = imagePath;
        this.description = description;
        this.userRole = userRole;
        this.blocked = blocked;
        this.productList = productList;
        this.reviewList = reviewList;
        this.averageRating = averageRating;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", imagePath='" + imagePath + '\'' +
                ", description='" + description + '\'' +
                ", userRole=" + userRole +
                ", blocked=" + blocked +
                ", productList=" + productList +
                ", reviewList=" + reviewList +
                ", averageRating=" + averageRating +
                '}';
    }
}
