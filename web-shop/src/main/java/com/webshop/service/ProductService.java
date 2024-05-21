package com.webshop.service;
import com.webshop.dto.ProductDto;
import com.webshop.dto.ProductFilterDto;
import com.webshop.exception.CategoryNotFoundException;
import com.webshop.exception.ProductNotFoundException;
import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.SalesType;
import com.webshop.repository.CategoryRepository;
import com.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    public Category findCategoryByName(String categoryName){
        return categoryRepository.findByCategoryName(categoryName).orElseThrow(() ->new CategoryNotFoundException("Category not found"));
    }

    public Product updateProduct(Long id, Product updatedProduct) throws Exception {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) { throw new ProductNotFoundException("Product not found");}

        if (existingProduct.getSalesType() == SalesType.AUCTION && existingProduct.hasOffers()) {throw new Exception("Cannot update product on auction with existing offers");}

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImagePath(updatedProduct.getImagePath());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setReleaseDate(updatedProduct.getReleaseDate());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setSalesType(updatedProduct.getSalesType());

        return productRepository.save(existingProduct);
    }
}
