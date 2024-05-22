package com.webshop.controller;

import com.webshop.dto.CategoryDto;
import com.webshop.dto.CreateCategoryDto;
import com.webshop.exception.AccountNotFoundException;
import com.webshop.exception.CategoryNotFoundException;
import com.webshop.model.Account;
import com.webshop.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category/")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("all")
    public List<CategoryDto> getAllCategorys(){return categoryService.getAllCategorys();}

    @PostMapping("create-category")
    public ResponseEntity<String>createCategory(@RequestBody CreateCategoryDto createCategoryDto, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>("Not logged in", HttpStatus.UNAUTHORIZED);
        }
        try{
            categoryService.createCategory(createCategoryDto,account);
            return ResponseEntity.ok("Category created");
        }catch (AccountNotFoundException | CategoryNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
