package com.webshop.dto;

import com.webshop.model.Product;
import com.webshop.model.SalesType;

public class CreateProductDto {
    private String name;
    private String description;
    private Double price;
    private String imagePath;
    private String categoryName;
    private SalesType salesType;

    public CreateProductDto() {}

    public CreateProductDto(String name, String description, Double price, String imagePath, String categoryName, SalesType salesType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.categoryName = categoryName;
        this.salesType = salesType;

    }

    public CreateProductDto(Product product) {

        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imagePath = product.getImagePath();
        this.categoryName=product.getCategory().getCategoryName();
        this.salesType=product.getSalesType();
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public SalesType getSalesType() {
        return salesType;
    }

    public void setSalesType(SalesType salesType) {
        this.salesType = salesType;
    }
}
