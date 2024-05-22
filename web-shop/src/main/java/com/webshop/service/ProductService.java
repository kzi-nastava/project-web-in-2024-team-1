package com.webshop.service;
import com.webshop.dto.*;
import com.webshop.exception.*;
import com.webshop.model.*;
import com.webshop.repository.CategoryRepository;
import com.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Product findOne(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    //pocetak metoda za filtriranje
    public List<ProductDto> findProductByFilter(ProductFilterDto filterDto) {
        if (filterDto.getCategoryName() != null && filterDto.getStartPrice() != null && filterDto.getEndPrice() != null && filterDto.getSalesType() != null) {
            return findByCategoryAndPriceAndSalesType(filterDto);
        } else if (filterDto.getCategoryName() != null && filterDto.getStartPrice() != null && filterDto.getEndPrice() != null) {
            return findByCategoryAndPrice(filterDto);
        } else if (filterDto.getCategoryName() != null && filterDto.getSalesType() != null) {
            return findByCategoryAndSalesType(filterDto);
        } else if (filterDto.getStartPrice() != null && filterDto.getEndPrice() != null && filterDto.getSalesType() != null) {
            return findByPriceAndSalesType(filterDto);
        } else if (filterDto.getCategoryName() != null) {
            return findByCategory(filterDto);
        } else if (filterDto.getStartPrice() != null && filterDto.getEndPrice() != null) {
            return findByPrice(filterDto);
        } else if (filterDto.getSalesType() != null) {
            return findBySalesType(filterDto);
        } else {
            return findAllProducts();
        }
    }
    private List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return mapProductsToDto(products);
    }
    public List<ProductDto> findByCategoryAndPriceAndSalesType(ProductFilterDto filterDto) {
        List<Product> products = productRepository.findByCategory_CategoryNameAndPriceBetweenAndSalesType(
                filterDto.getCategoryName(),
                filterDto.getStartPrice(),
                filterDto.getEndPrice(),
                filterDto.getSalesType()
        );
        return mapProductsToDto(products);
    }

    public List<ProductDto> findByCategoryAndPrice(ProductFilterDto filterDto) {
        List<Product> products = productRepository.findByCategory_CategoryNameAndPriceBetween(
                filterDto.getCategoryName(),
                filterDto.getStartPrice(),
                filterDto.getEndPrice()
        );
        return mapProductsToDto(products);
    }

    public List<ProductDto> findByCategoryAndSalesType(ProductFilterDto filterDto) {
        List<Product> products = productRepository.findByCategory_CategoryNameAndSalesType(
                filterDto.getCategoryName(),
                filterDto.getSalesType()
        );
        return mapProductsToDto(products);
    }

    public List<ProductDto> findByPriceAndSalesType(ProductFilterDto filterDto) {
        List<Product> products = productRepository.findByPriceBetweenAndSalesType(
                filterDto.getStartPrice(),
                filterDto.getEndPrice(),
                filterDto.getSalesType()
        );
        return mapProductsToDto(products);
    }

    public List<ProductDto> findByCategory(ProductFilterDto filterDto) {
        List<Product> products = productRepository.findByCategory_CategoryName(filterDto.getCategoryName());
        return mapProductsToDto(products);
    }

    public List<ProductDto> findByPrice(ProductFilterDto filterDto) {
        // Check if both start and end prices are provided
        if (filterDto.getStartPrice() != null && filterDto.getEndPrice() != null) {
            // Ensure that start price is less than or equal to end price
            if (filterDto.getStartPrice() <= filterDto.getEndPrice()) {
                // Retrieve products within the specified price range
                List<Product> products = productRepository.findByPriceBetween(
                        filterDto.getStartPrice(),
                        filterDto.getEndPrice()
                );
                // Map the retrieved products to DTOs and filter out products with prices outside the range
                List<ProductDto> filteredProducts = new ArrayList<>();
                for (Product product : products) {
                    if (product.getPrice() >= filterDto.getStartPrice() && product.getPrice() <= filterDto.getEndPrice()) {
                        filteredProducts.add(new ProductDto(product));
                    }
                }
                return filteredProducts;
            } else {
                // If start price is greater than end price, return an empty list
                return new ArrayList<>();
            }
        } else {
            // If start and end prices are not both provided, return an empty list
            return new ArrayList<>();
        }
    }


    public List<ProductDto> findBySalesType(ProductFilterDto filterDto) {
        List<Product> products = productRepository.findBySalesType(filterDto.getSalesType());
        return mapProductsToDto(products);
    }

    // Helper method to map Product entities to ProductDto
    private List<ProductDto> mapProductsToDto(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto(product);
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }

//kraj metode za filtriranje
    public List<Product> findProductsByCategoryName(String categoryName) {
        return productRepository.findByCategory_CategoryName(categoryName);
    }

    public boolean hasOffers(Long productId) throws Exception {
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("Product not found"));
        return product.getOffers() != null && !product.getOffers().isEmpty();
    }


    private boolean isSeller(Account account){return account != null && account.getUserRole() == Role.SELLER;}

    private boolean isCustomer(Account account){return account != null && account.getUserRole() == Role.CUSTOMER; }



    public Product updateProduct(Long id, ProductDto updatedProduct, Account currentAccount) throws Exception {

        if (!isSeller(currentAccount)) {
            throw new AccountRoleException("You do not have permission to update this product");
        }

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found");
        }

        if (existingProduct.getSalesType() == SalesType.AUCTION && hasOffers(id)) {
            throw new Exception("Cannot update product on auction with existing offers");
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImagePath(updatedProduct.getImagePath());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setSalesType(updatedProduct.getSalesType());

        return productRepository.save(existingProduct);
    }


    public void createProduct(CreateProductDto createProductDto, Account currentAccount){

        if (!isSeller(currentAccount)) {
            throw new AccountRoleException("You do not have permission create a product");
        }

        String categoryName = createProductDto.getCategoryName();
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseGet(() -> categoryRepository.save(new Category(categoryName)));

        Product product = new Product();
        product.setName(createProductDto.getName());
        product.setDescription(createProductDto.getDescription());
        product.setImagePath(createProductDto.getImagePath());
        product.setPrice(createProductDto.getPrice());
        product.setCategory(category);
        product.setSalesType(createProductDto.getSalesType());
        product.setReleaseDate(new Date());
        productRepository.save(product);
    }


}

