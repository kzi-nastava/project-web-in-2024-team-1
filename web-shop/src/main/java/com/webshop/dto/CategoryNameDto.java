package com.webshop.dto;

public class CategoryNameDto {
    private String categoryName;

    public CategoryNameDto() {}
    public CategoryNameDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryNameDto(CategoryDto categoryDto) {
        this.categoryName = categoryDto.getCategoryName();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
