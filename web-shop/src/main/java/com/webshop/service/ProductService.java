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


    public void purchaseProduct(PurchaseProductDto purchaseProductDto, Account currentAccount,Long productId,OfferDto offerDto) {

        if (!isCustomer(currentAccount)) {
            throw new AccountRoleException("You do not have permission purchase a product");
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if (product.getSalesType() == SalesType.FIXED_PRICE) {
            if (product.getSold() == false) {
                product.setSold(true);
                productRepository.save(product);
                purchaseProductDto.setProductName(product.getName());
                purchaseProductDto.setPrice(product.getPrice());
                purchaseProductDto.setImagePath(product.getImagePath());
                purchaseProductDto.setSold(true);

                if(purchaseProductDto.getPurchaseProducts() == null){
                    purchaseProductDto.setPurchaseProducts(new ArrayList<>());
                }
                purchaseProductDto.getPurchaseProducts().add(new PurchaseProductDto(product.getName(),product.getPrice(),product.getImagePath(),product.getSold()));

            } else {
                throw new ProductIsSoldException("Product already purchased");
            }

        } else if (product.getSalesType() == SalesType.AUCTION) {
            if (isAuctionAvailable(product)) {
                if (offerDto.getPriceOffer() > offerDto.getCurrentPrice()) {
                    offerDto.setCurrentPrice(offerDto.getPriceOffer());
                    Offer offer = new Offer();
                    offer.setPriceOffer(offerDto.getPriceOffer());

                    offer.setAccount(currentAccount);

                    product.getOffers().add(offer);
                    product.setPrice(offerDto.getCurrentPrice());
                    productRepository.save(product);
                    if (product.getSold() == false) {
                        product.setSold(true);
                        productRepository.save(product);
                        purchaseProductDto.setProductName(product.getName());
                        purchaseProductDto.setPrice(product.getPrice());
                        purchaseProductDto.setImagePath(product.getImagePath());
                        purchaseProductDto.setSold(true);

                        if (purchaseProductDto.getPurchaseProducts() == null) {
                            purchaseProductDto.setPurchaseProducts(new ArrayList<>());
                        }
                        purchaseProductDto.getPurchaseProducts().add(new PurchaseProductDto(product.getName(), product.getPrice(), product.getImagePath(), product.getSold()));

                    }

                } else {
                    throw new InvalidBidException("Your bid must be higher than the current price");
                }
            } else {
                throw new AuctionNotActiveException("The auction is not active");
            }
        }else{ throw new SalesTypeException("This sales type is not supported for purchase"); }
}
    private boolean isAuctionAvailable(Product product){
        return product.getSalesType().equals(SalesType.AUCTION) && product.getSold().equals(false);}
}

