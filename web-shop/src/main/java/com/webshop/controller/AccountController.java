package com.webshop.controller;

import com.webshop.dto.*;
import com.webshop.exception.*;
import com.webshop.model.Account;
import com.webshop.model.Review;
import com.webshop.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;

@RestController
@RequestMapping(path = "/api/")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long id) {
        Account account = accountService.findOne(id);
        if(account == null) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        AccountDto accountDto = new AccountDto(account);
        return ResponseEntity.ok(account);
    }

    @GetMapping("accounts")
    public ResponseEntity<List<AccountDto>> getAccounts(HttpSession session){
        List<Account> accountList = accountService.findAll();
        Account account = (Account) session.getAttribute("account");
        if(account == null) {
            System.out.println("Account not found");
        } else {
            System.out.println("Account found");
        }

        List<AccountDto> accountDtoList = new ArrayList<>();
        for(Account accounts : accountList) {
            AccountDto accountDto = new AccountDto(accounts);
            accountDtoList.add(accountDto);
        }
        return ResponseEntity.ok(accountDtoList);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty()) {
            return new ResponseEntity<>("Username or password not correct", HttpStatus.BAD_REQUEST);
        }

        try {
            Account account = accountService.login(loginDto);
            session.setAttribute("account", account);
            return ResponseEntity.ok("Successfully logged in!");
        }catch (AuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto, HttpSession session) {
        try{
            accountService.register(registerDto);
            return ResponseEntity.ok("Successfully registered!");
        }catch (UsernameAlreadyExistsException | GmailAlreadyExistsException | PasswordNotCorrectException | AccountRoleException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update-account")
    public ResponseEntity<String> updateProfile(@RequestBody UpdateProfileBasicDto updateProfileBasicDto, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if(account == null) {
            return new ResponseEntity<>("Account not logged in", HttpStatus.UNAUTHORIZED);
        }
        accountService.updateAccount(account.getId(),updateProfileBasicDto);
        return ResponseEntity.ok("Account successfully updated!");
    }

    @PutMapping("update-advance-account")
    public ResponseEntity<String> updateAdvanceProfile(@RequestBody UpdateProfileAdvanceDto updateProfileAdvanceDto, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if(account == null) {
            return new ResponseEntity<>("Account not logged in", HttpStatus.UNAUTHORIZED);
        }
        try{
            accountService.updateAdvanceAccount(account.getId(),updateProfileAdvanceDto);
            return ResponseEntity.ok("Account advanced successfully updated!");
        } catch (PasswordNotCorrectException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
