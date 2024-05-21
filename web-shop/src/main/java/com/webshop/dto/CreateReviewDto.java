package com.webshop.dto;

import com.webshop.model.Account;
import com.webshop.model.Review;
import jakarta.persistence.*;

import java.util.Date;

public class CreateReviewDto {

    private int rating;

    private String comment;


    public CreateReviewDto() {}

    public CreateReviewDto(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public CreateReviewDto(Review review){
        this.rating = review.getRating();
        this.comment = review.getComment();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
