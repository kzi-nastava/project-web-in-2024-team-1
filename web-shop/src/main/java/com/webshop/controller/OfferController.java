package com.webshop.controller;

import com.webshop.dto.OfferDto;
import com.webshop.exception.AccountRoleException;
import com.webshop.exception.OfferNotFoundException;
import com.webshop.model.Account;
import com.webshop.service.OfferService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/offers/")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping("{offerId}")
    public ResponseEntity<OfferDto> getOffer(@PathVariable Long offerId, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try{
            List<OfferDto> offerDtos = offerService.getOffers(offerId,account);
            return new ResponseEntity<>(offerDtos.get(0), HttpStatus.OK);
        } catch (OfferNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (AccountRoleException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
