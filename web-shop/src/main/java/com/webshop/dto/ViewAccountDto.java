package com.webshop.dto;

import com.webshop.model.Account;
import com.webshop.model.Product;
import com.webshop.model.Review;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewAccountDto {
    private Long id;

    private String name;

    private String lastName;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String imagePath;

    private String description;

    private List<ProductDto> products;
    private List<ReviewDto> receivedReviews;
    private double averageRating;

    public ViewAccountDto() {}

    public ViewAccountDto(Long id, String name, String lastName, String phoneNumber, LocalDate dateOfBirth,
                          String imagePath, String description, List<ProductDto> products,List<ReviewDto> receivedReviews, double averageRating) {

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.imagePath = imagePath;
        this.description = description;
        this.averageRating = averageRating;
        this.products = products;
        this.receivedReviews = receivedReviews;
    }

    public ViewAccountDto(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.lastName = account.getLastName();
        this.phoneNumber = account.getPhoneNumber();
        this.dateOfBirth = account.getDateOfBirth();
        this.imagePath = account.getImagePath();
        this.description = account.getDescription();
        this.products = new ArrayList<ProductDto>();
        this.receivedReviews = new ArrayList<ReviewDto>();
        this.averageRating = account.getAverageRating();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public List<ReviewDto> getReceivedReviews() {
        return receivedReviews;
    }

    public void setReceivedReviews(List<ReviewDto> receivedReviews) {
        this.receivedReviews = receivedReviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
