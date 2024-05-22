package com.webshop.repository;

import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.SalesType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByName(String name);
    List<Product> findByDescription(String description);
    List<Product> findByCategory_CategoryName(String categoryName);

}
