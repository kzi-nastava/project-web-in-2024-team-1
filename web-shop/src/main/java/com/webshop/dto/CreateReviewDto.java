package com.webshop.dto;

import com.webshop.model.Account;
import com.webshop.model.Review;
import jakarta.persistence.*;

import java.util.Date;

public class CreateReviewDto {

    private int Rating;

    private String Comment;


    public CreateReviewDto() {}

    public CreateReviewDto(int rating, String comment) {
        this.Rating = rating;
        this.Comment = comment;
    }

    public CreateReviewDto(Review review){
        this.Rating = review.getRating();
        this.Comment = review.getComment();
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
