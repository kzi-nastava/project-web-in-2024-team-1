package com.webshop.dto;

import com.webshop.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto
{
    private Long id;
    private String categoryName;
    private List<ProductDto> products;

    public CategoryDto() { }

    public CategoryDto(Long id, String categoryName, List<ProductDto> products)
    {
        this.id = id;
        this.categoryName = categoryName;
        this.products = products;
    }

    public CategoryDto(Category category)
    {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.products = new ArrayList<ProductDto>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
