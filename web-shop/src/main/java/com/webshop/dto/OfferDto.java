package com.webshop.dto;

import com.webshop.model.Offer;

public class OfferDto {
    private Double currentPrice;
    private Double priceOffer;
    private Long customerOfferId;

    public OfferDto() {
    }

    public OfferDto(Double currentPrice, Double priceOffer, Long customerOfferId) {
        this.currentPrice = currentPrice;
        this.priceOffer = priceOffer;
        this.customerOfferId = customerOfferId;
    }

    public OfferDto(Offer offer) {
        this.currentPrice = offer.getCurrentPrice();
        this.priceOffer= offer.getPriceOffer();
        this.customerOfferId= offer.getAccount().getId();
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
    }

    public Long getCustomerOfferId() {
        return customerOfferId;
    }

    public void setCustomerOfferId(Long customerOfferId) {
        this.customerOfferId = customerOfferId;
    }
}
