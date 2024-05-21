package com.webshop.controller;

import com.webshop.dto.ProductDto;
import com.webshop.dto.ProductFilterDto;
import com.webshop.exception.AccountNotFoundException;
import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search-by-name")
    public ResponseEntity<List<ProductDto>> getProductByName(HttpSession session)
    {
        Product product= (Product) session.getAttribute("product");
        if(product == null) {return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);}

        String productName = product.getName();
        List<ProductDto> productDtoList = new ArrayList<>();
        try
        {
            List<Product> productList = productService.findByName(productName);
            for(Product product1 : productList)
            {
                 ProductDto productDto = new ProductDto(product1);
                 productDtoList.add(productDto);
            }
            return ResponseEntity.ok(productDtoList);
        } catch (Exception e){return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}
    }

    @GetMapping("/searh-by-description")
    public ResponseEntity<List<ProductDto>> getProductByDescription(HttpSession session)
    {
        Product product=(Product) session.getAttribute("product");
        if(product == null) {return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);}
        String description = product.getDescription();
        List<ProductDto>productDtoList = new ArrayList<>();
        try
        {
            List<Product> productList = productService.findByName(description);
            for(Product product1 : productList)
            {
                ProductDto productDto = new ProductDto(product1);
                productDtoList.add(productDto);
            }
            return ResponseEntity.ok(productDtoList);
        } catch (Exception e){return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}
    }

//    @PostMapping("/filter")
//    public ResponseEntity<List<ProductDto>> filterProducts(@RequestBody ProductFilterDto filterDto) {
//        List<ProductDto> productDtoList = productService.findProductByCategoryAndPriceAndSalesType(filterDto);
//        return ResponseEntity.ok(productDtoList);
//    }

    @PutMapping("product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        try {
            Category category = productService.findCategoryByName(productDto.getCategoryName());

            Product updatedProduct = new Product(
                    productDto.getName(),
                    productDto.getDescription(),
                    productDto.getPrice(),
                    productDto.getSalesType(),
                    productDto.getReleaseDate(),
                    productDto.getImagePath()
            );
            updatedProduct.setCategory(category);
            productService.updateProduct(id, updatedProduct);
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
