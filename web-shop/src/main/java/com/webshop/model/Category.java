package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String CategoryName;


    public Category() {
        this(1L,"");
    }

    public Category(Long id, String categoryName) {
        this.id = id;
        this.CategoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName = categoryName;
    }


}
