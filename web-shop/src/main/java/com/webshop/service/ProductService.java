package com.webshop.service;
import com.webshop.model.Product;
import com.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findByName(String productName) {
        Optional<List<Product>> product = productRepository.findByName(productName);
        if(product.isPresent()){return product.get();}

        return product.orElse(Collections.emptyList());
    }

    public Product findByDescription(String productDescription) {
        Optional<Product> product = productRepository.findByDescription(productDescription);
        if(product.isPresent()){ return product.get();}

        return null;
    }

    public List<Product> findAll() { return productRepository.findAll(); }
    public Product save(Product product) { return productRepository.save(product);}
    public Product findOne(Long id) { return productRepository.findById(id).orElse(null);}

}
