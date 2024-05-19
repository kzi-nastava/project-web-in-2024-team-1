package com.webshop.repository;

import com.webshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long>
{
    List<Category> findByCategoryName(String categoryName);
}
