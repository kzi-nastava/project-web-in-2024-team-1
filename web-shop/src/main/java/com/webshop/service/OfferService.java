package com.webshop.service;

import com.webshop.dto.OfferDto;
import com.webshop.exception.AccountRoleException;
import com.webshop.exception.OfferNotFoundException;
import com.webshop.model.*;
import com.webshop.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    private boolean isSeller(Account account){return account != null && account.getUserRole() == Role.SELLER;}

    public List<OfferDto> getOffers(Long offerId, Account currentAccount){
        if(!isSeller(currentAccount)){
            throw new AccountRoleException("You do not have permission to see price offers");
        }

        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new OfferNotFoundException("Offer not found"));
        OfferDto offerDto = new OfferDto();
        offerDto.setPriceOffer(offer.getPriceOffer());
        offerDto.setCurrentPrice(offer.getCurrentPrice());
        offerDto.setCustomerOfferId(offer.getAccount().getId());
        List<OfferDto> offerDtos = new ArrayList<>();
        offerDtos.add(offerDto);
        return offerDtos;

    }

}
