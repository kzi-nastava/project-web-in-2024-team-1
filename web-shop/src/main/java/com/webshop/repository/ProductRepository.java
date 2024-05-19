package com.webshop.repository;

import com.webshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByName(String name);
    List<Product> findByDescription(String description);
}
