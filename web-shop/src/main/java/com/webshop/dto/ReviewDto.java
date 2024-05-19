package com.webshop.dto;

import com.webshop.model.Account;
import com.webshop.model.Review;
import jakarta.persistence.*;

import java.util.Date;

public class ReviewDto {
    private Long id;

    private int Rating;

    private String Comment;

    private Date ReviewDate;

    //private Account user;
    private Account Reviewer;
    private Account Review;

    public ReviewDto() {}
    public ReviewDto(Long id, int Rating, String Comment, Date ReviewDate, Account Reviewer, Account Review) {
        this.id = id;
        this.Rating = Rating;
        this.Comment = Comment;
        this.ReviewDate = ReviewDate;
        this.Reviewer = Reviewer;
        this.Review = Review;
    }

    public ReviewDto(Review review){
        this.id = review.getId();
        this.Rating = review.getRating();
        this.Comment = review.getComment();
        this.ReviewDate = review.getReviewDate();
        this.Reviewer = review.getReviewer();
        this.Review = review.getReviewedUser();
        //this.user = review.getReviewer();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        ReviewDate = reviewDate;
    }

   /* public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }*/

    public Account getReviewer() {
        return Reviewer;
    }

    public void setReviewer(Account reviewer) {
        Reviewer = reviewer;
    }

    public Account getReview() {
        return Review;
    }
    public void setReview(Account review) {
        Review = review;
    }
}
