package com.webshop.service;

import com.webshop.dto.*;
import com.webshop.exception.*;
import com.webshop.model.*;
import com.webshop.repository.AccountRepository;
import com.webshop.repository.ProductRepository;
import com.webshop.repository.ReviewRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewService reviewService;

    public Account findOne(Long id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return account.get();
        }
        return null;
    }

    public ViewAccountDto findOneDto(Long id){
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null){

            return null;

        }
        ViewAccountDto viewAccountDto = new ViewAccountDto(account);

        if(account.getUserRole() == Role.SELLER){
            List<Product> products= productRepository.findProductsBySeller(account);
            List<ProductDto> productDtos = new ArrayList<>();
            for (Product product : products) {
                productDtos.add(new ProductDto(product));
            }
            viewAccountDto.setProducts(productDtos);

            List<Review> reviews = reviewService.getReviewsByReviewer(account.getId());
            List<ReviewDto> reviewDtos = new ArrayList<>();
            for (Review review : reviews) {
                reviewDtos.add(new ReviewDto(review));
            }
            viewAccountDto.setReceivedReviews(reviewDtos);
        } else if(account.getUserRole() == Role.CUSTOMER){
            List<Product> products1 = productRepository.findProductsByBuyer(account);
            List<ProductDto> productDtos = new ArrayList<>();
            for (Product product : products1) {
                productDtos.add(new ProductDto(product));
            }
            viewAccountDto.setProducts(productDtos);

            List<Review> reviews = reviewService.getReviewsByReviewer(account.getId());
            List<ReviewDto> reviewDtos = new ArrayList<>();
            for (Review review : reviews) {
                reviewDtos.add(new ReviewDto(review));
            }
            viewAccountDto.setReceivedReviews(reviewDtos);
        } else{
            throw new AccountRoleException("Account role not supported");
        }

        return viewAccountDto;

    }



    public Account save(Account account){
        return accountRepository.save(account);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findByEmail(String email){
        Optional<Account> account = accountRepository.findByEmail(email);

        return account.orElse(null);
    }

    public Account findByUsername(String username){
        Optional<Account> account = accountRepository.findByUsername(username);

        return account.orElse(null);
    }

    public void register(RegisterDto registerDto) {
        if (accountRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (accountRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new GmailAlreadyExistsException("Email already exists");
        }

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new PasswordNotCorrectException("Passwords do not match");
        }

        Role role = registerDto.getRole();
        if(role != Role.CUSTOMER && role != Role.SELLER){
            throw new AccountRoleException("Role not allowed");
        }

        Account account = new Account();
        account.setName(registerDto.getName());
        account.setLastName(registerDto .getLastName());
        account.setUsername(registerDto.getUsername());
        account.setEmail(registerDto.getEmail());
        account.setPhoneNumber(registerDto.getPhoneNumber());
        account.setPassword(registerDto.getPassword());
        account.setUserRole(registerDto.getRole());
        save(account);
    }

    public Account login(LoginDto loginDto) {
        Optional<Account> account = accountRepository.findByUsername(loginDto.getUsername());
        if(account.isEmpty()){
           throw new AuthenticationException("Account username not valid");
        }
        if(!account.get().getPassword().equals(loginDto.getPassword())){
            throw new AuthenticationException("Wrong password");
        }
        return account.get();
    }

    public void logout(HttpSession session){
        session.invalidate();
    }

    public void updateAccount(Long id, UpdateProfileBasicDto updateProfileBasicDto) {
        Account account = findOne(id);
        if(account == null){
            throw new AccountNotFoundException("Account not found");
        }

        account.setName(updateProfileBasicDto.getName());
        account.setLastName(updateProfileBasicDto.getLastName());
        account.setPhoneNumber(updateProfileBasicDto.getPhoneNumber());
        account.setDateOfBirth(updateProfileBasicDto.getDateOfBirth());
        account.setImagePath(updateProfileBasicDto.getImagePath());
        account.setDescription(updateProfileBasicDto.getDescription());
        save(account);
    }

    public void updateAdvanceAccount(Long id, UpdateProfileAdvanceDto updateProfileAdvanceDto) {
        Account account = findOne(id);
        if(account == null){
            throw new AccountNotFoundException("Account not found");
        }

        if(!account.getPassword().equals(updateProfileAdvanceDto.getCurrentPassword())){
            throw new AuthenticationException("Wrong password");
        }

        if(updateProfileAdvanceDto.getNewUsername() != null && !updateProfileAdvanceDto.getNewUsername().isEmpty()){
            account.setUsername(updateProfileAdvanceDto.getNewUsername());
        }

        if(updateProfileAdvanceDto.getNewEmail() != null && !updateProfileAdvanceDto.getNewEmail().isEmpty()){
            account.setEmail(updateProfileAdvanceDto.getNewEmail());
        }

        if(updateProfileAdvanceDto.getNewPassword() != null && !updateProfileAdvanceDto.getNewPassword().isEmpty()){
            account.setPassword(updateProfileAdvanceDto.getNewPassword());
        }

        save(account);
    }



}
