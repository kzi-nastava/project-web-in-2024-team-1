package com.webshop.dto;

import com.webshop.model.Product;
import com.webshop.model.SalesType;

public class ProductFilterDto
{
    private Long Id;
    private Double startPrice;
    private Double endPrice;
    private String categoryName;
    private SalesType salesType;

    public ProductFilterDto() { }

    public ProductFilterDto(Long id, Double startPrice, Double endPrice, String categoryName, SalesType salesType)
    {
        Id = id;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.categoryName = categoryName;
        this.salesType = salesType;
    }

    public ProductFilterDto(Product product){
        this.Id = product.getId();
        this.startPrice = product.getPrice();
        this.endPrice = product.getPrice();
        this.categoryName=product.getCategory().getCategoryName();
        this.salesType=product.getSalesType();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(SalesType salesType) {
        this.salesType = salesType;
    }

}
