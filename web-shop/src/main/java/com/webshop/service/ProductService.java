package com.webshop.service;
import com.webshop.model.Product;
import com.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product finfByName(String productName) {
        Optional<Product> product = productRepository.findByName(productName);
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    public Product findByDescription(String productDescription) {
        Optional<Product> product = productRepository.findByDescription(productDescription);
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
