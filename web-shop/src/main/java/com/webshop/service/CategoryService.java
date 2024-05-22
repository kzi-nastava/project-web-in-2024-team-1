package com.webshop.service;

import com.webshop.dto.CategoryDto;
import com.webshop.dto.CreateCategoryDto;
import com.webshop.dto.CreateProductDto;
import com.webshop.exception.AccountRoleException;
import com.webshop.exception.CategoryAlreadyExistsException;
import com.webshop.model.Account;
import com.webshop.model.Category;
import com.webshop.model.Role;
import com.webshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() { return categoryRepository.findAll(); }
    public Optional<Category> findByCategoryName(String categoryName) { return categoryRepository.findByCategoryName(categoryName); }
    public Category findOne(Long id){return categoryRepository.findById(id).orElse(null);}

    public List<CategoryDto>getAllCategorys(){
        List<Category>categoryList = categoryRepository.findAll();
        List<CategoryDto>categoryDtoList = new ArrayList<CategoryDto>();

        for(Category category : categoryList){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setCategoryName(category.getCategoryName());
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    public void createCategory(CreateCategoryDto createCategoryDto, Account currentAccount){
        if (!isSeller(currentAccount)) {
            throw new AccountRoleException("You do not have permission create a category");
        }
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(createCategoryDto.getCategoryName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
        Category category=new Category();
        category.setCategoryName(createCategoryDto.getCategoryName());

        categoryRepository.save(category);

    }

    private boolean isSeller(Account account){
        return account != null && account.getUserRole() == Role.SELLER;
    }
}

