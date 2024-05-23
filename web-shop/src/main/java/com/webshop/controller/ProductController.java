package com.webshop.controller;

import com.webshop.dto.*;
import com.webshop.exception.AccountNotFoundException;
import com.webshop.exception.ProductNotFoundException;
import com.webshop.model.Account;
import com.webshop.model.Product;
import com.webshop.model.SalesType;
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

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        Product product = productService.findOne(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDto productDto = new ProductDto(product);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/filter-products")
    public ResponseEntity<List<ProductDto>> filterProducts(@RequestParam(value = "categoryName", required = false) String categoryName,
                                                           @RequestParam(value = "startPrice", required = false) Double startPrice,
                                                           @RequestParam(value = "endPrice", required = false) Double endPrice,
                                                           @RequestParam(value = "salesType", required = false) String salesType) {
        try {
            // Create a ProductFilterDto object with the provided parameters
            ProductFilterDto filterDto = new ProductFilterDto();
            filterDto.setCategoryName(categoryName);
            filterDto.setStartPrice(startPrice);
            filterDto.setEndPrice(endPrice);
            // Parse the SalesType string to the enum type
            filterDto.setSalesType(salesType != null ? SalesType.valueOf(salesType.toUpperCase()) : null);

            // Call the service method to filter products
            List<ProductDto> filteredProducts = productService.findProductByFilter(filterDto);

            if (filteredProducts.isEmpty()) {
                // Return 404 Not Found if no products match the criteria
                return ResponseEntity.notFound().build();
            }

            // Return the filtered products
            return ResponseEntity.ok(filteredProducts);
        } catch (Exception e) {
            // Return a 500 Internal Server Error with the exception message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/search-by-name")
    public ResponseEntity<List<ProductDto>> getProductByName(@RequestParam("name") String productName) {
        List<ProductDto> productDtoList = new ArrayList<>();
        try {
            List<Product> productList = productService.findByName(productName);
            for (Product product : productList) {
                ProductDto productDto = new ProductDto(product);
                productDtoList.add(productDto);
            }
            return ResponseEntity.ok(productDtoList);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search-by-description")
    public ResponseEntity<List<ProductDto>> getProductByDescription(@RequestParam("description") String productDescription) {
        List<ProductDto> productDtoList = new ArrayList<>();
        try {
            List<Product> productList = productService.findByDescription(productDescription);
            for (Product product : productList) {
                ProductDto productDto = new ProductDto(product);
                productDtoList.add(productDto);
            }
            return ResponseEntity.ok(productDtoList);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("update-product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto,HttpSession session ) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {return new ResponseEntity<>("Not logged in", HttpStatus.UNAUTHORIZED);}
        try{
            productService.updateProduct(id, productDto,account);
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("create-product")
    public ResponseEntity<String>createProduct(@RequestBody CreateProductDto createProductDto, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>("Not logged in", HttpStatus.UNAUTHORIZED);
        }
        try{
            productService.createProduct(createProductDto,account);
            return ResponseEntity.ok("Product created");
        }catch (AccountNotFoundException | ProductNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("purchase/{productId}")
    public ResponseEntity<String> purchaseProduct(@PathVariable Long productId,@RequestBody PurchaseProductDto purchaseProductDto, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>("Not logged in", HttpStatus.UNAUTHORIZED);
        }
        try{
            productService.purchaseProduct(purchaseProductDto,account,productId);
            return new ResponseEntity<>("Product purchased successfully", HttpStatus.OK);
        } catch (AccountNotFoundException | ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("purchased-products")
    public ResponseEntity<List<PurchaseProductDto>> getPurchasedProducts(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<PurchaseProductDto> purchaseProductDtos = productService.getPurchaseProducts(account);
        return ResponseEntity.ok(purchaseProductDtos);
    }

    @PostMapping("auction/{productId}")
    public ResponseEntity<String> auctionProduct(@PathVariable Long productId,@RequestBody OfferDto offerDto, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>("Not logged in", HttpStatus.UNAUTHORIZED);
        }
        try{
            productService.auctionProduct(account,productId,offerDto);
            return ResponseEntity.ok("Offer sent successfully");
        } catch (AccountNotFoundException | ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("purchase")
    public ResponseEntity<List<PuchaseActionDto>> getPurchaseActions(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<PuchaseActionDto> purchaseActions = productService.getPurchaseActions(account);
        return ResponseEntity.ok(purchaseActions);
    }

    @GetMapping("sale")
    public ResponseEntity<List<PuchaseActionDto>> getSaleActions(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<PuchaseActionDto> saleActions = productService.getSaleActions(account);
        return ResponseEntity.ok(saleActions);
    }

}
