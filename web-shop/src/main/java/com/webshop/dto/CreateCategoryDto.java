package com.webshop.dto;

import com.webshop.model.Category;

public class CreateCategoryDto {

    private String categoryName;

    public CreateCategoryDto() {
    }

    public CreateCategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public CreateCategoryDto(Category category) {
        this.categoryName = category.getCategoryName();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
