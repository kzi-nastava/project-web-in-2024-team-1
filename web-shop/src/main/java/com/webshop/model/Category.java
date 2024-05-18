package com.webshop.model;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
public class Category implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoryName;

    public Category() {
        this("");
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
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

    @Override
    public String toString()
    {
        return "Category{" +
                "id=" + id +
                ", CategoryName='" + categoryName + '\'' +
                '}';
    }
}
