package com.webshop.dto;

import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.SalesType;

import java.time.LocalDate;

public class ProductDto
{

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imagePath;
    private String categoryName;
    private SalesType salesType;

    public ProductDto(){}

    public ProductDto(Long id, String name, String description, Double price, String imagePath
                     , String category, SalesType salesType)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.categoryName = category;
        this.salesType = salesType;
    }

    public ProductDto(Product product)
    {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imagePath = product.getImagePath();
        this.categoryName = product.getCategory().getCategoryName();
        this.salesType=product.getSalesType();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price;}

    public void setPrice(Double price) { this.price = price; }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String category) { this.categoryName = category; }

    public SalesType getSalesType() { return salesType; }

    public void setSalesType(SalesType salesType) { this.salesType = salesType; }
}
