package com.webshop.service;

import com.webshop.dto.*;
import com.webshop.exception.UsernameAlreadyExistsException;
import com.webshop.exception.GmailAlreadyExistsException;
import com.webshop.exception.AccountRoleException;
import com.webshop.exception.PasswordNotCorrectException;
import com.webshop.exception.AuthenticationException;
import com.webshop.exception.AccountNotFoundException;
import com.webshop.exception.ReviewAndReviewedException;
import com.webshop.model.Account;
import com.webshop.model.Review;
import com.webshop.model.Role;
import com.webshop.repository.AccountRepository;
import com.webshop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public Account findOne(Long id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return account.get();
        }
        return null;
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
