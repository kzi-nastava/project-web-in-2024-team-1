package com.webshop.service;

import com.webshop.dto.RegisterDto;
import com.webshop.model.Account;
import com.webshop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

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
        if(account.isPresent()){
            return account.get();
        }
        return null;
    }

    public Account findByUsername(String username){
        Optional<Account> account = accountRepository.findByUsername(username);
        if(account.isPresent()){
            return account.get();
        }
        return null;
    }

    public void register(RegisterDto registerDto){
       /* if(accountRepository.findByUsername(registerDto.getUsername()).isPresent()){
            throws new Exception("Username already exists");
        }*/
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

}
