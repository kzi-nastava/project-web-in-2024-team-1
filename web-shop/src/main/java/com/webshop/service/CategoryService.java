package com.webshop.service;

import com.webshop.model.Category;
import com.webshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() { return categoryRepository.findAll(); }
    public List<Category>findByCategoryName(String categoryName) { return categoryRepository.findByCategoryName(categoryName); }
    public Category findOne(Long id){return categoryRepository.findById(id).orElse(null);}
}

