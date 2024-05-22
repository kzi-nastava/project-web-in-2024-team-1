package com.webshop.dto;

import com.webshop.model.Product;

import java.util.List;

public class PurchaseProductDto {
    private String productName;
    private Double price;
    private String imagePath;
    private Boolean isSold=true;
    private List<PurchaseProductDto> purchaseProducts;


    public PurchaseProductDto() { }

    public PurchaseProductDto(String productName, Double price, String imagePath, Boolean isSold) {
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
        this.isSold = isSold;
    }

    public PurchaseProductDto(Product product) {
        this.productName = product.getName();
        this.price = product.getPrice();
        this.imagePath = product.getImagePath();
        this.isSold=product.getSold();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    public List<PurchaseProductDto> getPurchaseProducts() {
        return purchaseProducts;
    }

    public void setPurchaseProducts(List<PurchaseProductDto> purchaseProducts) {

        this.purchaseProducts = purchaseProducts;
    }

}
