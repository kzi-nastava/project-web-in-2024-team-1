package com.webshop.repository;


import com.webshop.model.Account;
import com.webshop.model.Category;

import com.webshop.model.Product;
import com.webshop.model.SalesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByName(String name);
    List<Product> findByDescription(String description);

    List<Product> findByCategory_CategoryName(String categoryName);

    List<Product> findByBuyer(Account buyer);
    List<Product> findBySeller(Account seller);


//    @Query("SELECT p FROM Product p WHERE " +
//            "(:categoryName IS NULL OR p.category.categoryName = :categoryName) AND " +
//            "(:startPrice IS NULL OR p.price >= :startPrice) AND " +
//            "(:endPrice IS NULL OR p.price <= :endPrice) AND " +
//            "(:salesType IS NULL OR p.salesType = :salesType)")
    List<Product> findAllByCategory_CategoryNameAndPriceBetweenAndSalesType(
            String categoryName,
            Double startPrice,
            Double endPrice,
            SalesType salesType
    );

    List<Product> findByCategory_CategoryNameAndPriceBetweenAndSalesType(
            String categoryName,
            Double startPrice,
            Double endPrice,
            SalesType salesType
    );

    List<Product> findByCategory_CategoryNameAndPriceBetween(
            String categoryName,
            Double startPrice,
            Double endPrice
    );

    List<Product> findByCategory_CategoryNameAndSalesType(
            String categoryName,
            SalesType salesType
    );

    List<Product> findByPriceBetweenAndSalesType(
            Double startPrice,
            Double endPrice,
            SalesType salesType
    );


    List<Product> findByPriceBetween(
            Double startPrice,
            Double endPrice
    );

    List<Product> findBySalesType(SalesType salesType);
}
