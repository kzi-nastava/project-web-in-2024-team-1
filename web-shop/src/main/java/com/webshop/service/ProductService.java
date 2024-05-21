package com.webshop.service;
import com.webshop.dto.ProductDto;
import com.webshop.dto.ProductFilterDto;
import com.webshop.model.Product;
import com.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findByName(String productName) {return productRepository.findByName(productName);}
    public List<Product> findByDescription(String productDescription) {return productRepository.findByDescription(productDescription); }
    public List<Product> findAll() { return productRepository.findAll(); }
    public Product save(Product product) { return productRepository.save(product);}
    public Product findOne(Long id) { return productRepository.findById(id).orElse(null);}

   public List<ProductDto> findProductByCategoryAndPriceAndSalesType(ProductFilterDto filterDto)
   {
       /*List<Product> products = productRepository.findProductByCategoryAndPriceAndSalesType(
               filterDto.getStartPrice(),
               filterDto.getEndPrice(),
               filterDto.getCategoryName(),
               filterDto.getSalesType()
       );*/
       List<ProductDto> productDtos = new ArrayList<>();
       /*for (Product product : products) {
           productDtos.add(new ProductDto(product));
       }*/
       return productDtos;
   }
}
