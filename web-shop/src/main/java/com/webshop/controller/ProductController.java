package com.webshop.controller;

import com.webshop.dto.ProductDto;
import com.webshop.model.Product;
import com.webshop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public ResponseEntity<List<ProductDto>> getAllProducts(HttpSession session)
    {
        List<Product> productList = productService.findAll();

        Product product1 = (Product) session.getAttribute("product");
        if(product1 == null){System.out.println("Product not found");}

        else {System.out.println("Product found");}

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : productList)
        {
            ProductDto productDto = new ProductDto(product);
            productDtoList.add(productDto);
        }

        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product>getProductById(@PathVariable("id") Long id)

    {
        Product product = productService.findOne(id);
        if(product == null) {return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);}

        ProductDto productDto = new ProductDto(product);
        return ResponseEntity.ok(product);
    }

//     @GetMapping("search")
//    public ResponseEntity<List<Product>> searcProducts(String name, String description)
//     {
//         if (name != null && description != null) {
//             return productService.findByName(name);
//         } else if (name != null) {
//             return productRepository.findByNameContaining(name);
//         } else if (description != null) {
//             return productRepository.findByDescriptionContaining(description);
//         } else {
//             return productService.findAll();
//         }
//     }

}
