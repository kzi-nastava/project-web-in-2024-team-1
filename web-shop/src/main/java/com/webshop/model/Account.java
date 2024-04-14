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
    private String Name;

    @Column
    private String LastName;

    @Column(unique = true)
    private String Username;

    @Column(unique = true)
    private String Email;

    @Column
    private String PhoneNumber;

    @Column
    private String Password;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate DateOfBirth;

    @Column
    private String ImagePath;

    @Column
    private String Description;

    @Enumerated(EnumType.STRING)
    @Column
    private Role UserRole;

    @Column
    private Boolean Blocked;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ACCOUNT_PRODUCTS",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> ProductList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ACCOUNT_REVIEWS",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "review_id", referencedColumnName = "id")
    )
    private List<Review> ReviewList;

    @Column
    private double AverageRating;

    public Account() {
        this.id = 1L;
        this.Name = "";
        this.LastName = "";
        this.Username = "";
        this.Email = "";
        this.PhoneNumber = "";
        this.Password = "";
        this.DateOfBirth = LocalDate.MIN;
        this.ImagePath = "default.jpg";
        this.Description = "bio";
        this.UserRole = Role.CUSTOMER;
        this.Blocked = false;
        this.ProductList = new ArrayList<>();
        this.ReviewList = new ArrayList<>();
        this.AverageRating = 1.0;
    }


    public Account(Long id, String name, String lastName, String username, String email, String phoneNumber, String password, LocalDate dateOfBirth, String imagePath, String description, Role userRole, Boolean blocked, List<Product> productList, List<Review> rewievsList, Double averageRating ) {
        this.id = id;
        this.Name = name;
        this.LastName = lastName;
        this.Username = username;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.Password = password;
        this.DateOfBirth = dateOfBirth;
        this.ImagePath = imagePath;
        this.Description = description;
        this.UserRole = userRole;
        this.Blocked = blocked;
        this.ProductList = productList;
        this.ReviewList = rewievsList;
        this.AverageRating = averageRating;
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

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        this.ImagePath = imagePath;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public Role getUserRole() {
        return UserRole;
    }

    public void setUserRole(Role userRole) {
        this.UserRole = userRole;
    }

    public Boolean getBlocked() {
        return Blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.Blocked = blocked;
    }

    public List<Product> getProductList() {return ProductList;}

    public void setProductList(List<Product> productList) {this.ProductList = productList;}

    public List<Review> getReviewList() {return ReviewList;}

    public void setReviewList(List<Review> reviewList) {this.ReviewList = reviewList;}

    public double getAverageRating() {return AverageRating;}

    public void setAverageRating(double averageRating) {this.AverageRating = averageRating;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Username='" + Username + '\'' +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Password='" + Password + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", ImagePath='" + ImagePath + '\'' +
                ", Description='" + Description + '\'' +
                ", UserRole=" + UserRole +
                ", Blocked=" + Blocked +
                ", ProductList=" + ProductList +
                ", ReviewList=" + ReviewList +
                ", AverageRating=" + AverageRating +
                '}';
    }
}
