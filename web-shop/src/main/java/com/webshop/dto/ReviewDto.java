package com.webshop.dto;

import com.webshop.model.Review;

import java.util.Date;

public class ReviewDto {
    private Long id;
    private int rating;
    private String comment;
    private Date reviewDate;
    private Long reviewerId;
    private Long reviewedUserId;

    public ReviewDto() {}
    public ReviewDto(Long id, int rating, String comment, Date reviewDate, Long reviewedUserId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.reviewedUserId = reviewedUserId;

    }

    public ReviewDto(Review review){
        this.id = review.getId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.reviewDate = review.getReviewDate();
        this.reviewerId = review.getReviewer().getId();
        this.reviewedUserId = review.getReviewedUser().getId();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getReviewedUserId() {
        return reviewedUserId;
    }

    public void setReviewedUserId(Long reviewedUserId) {
        this.reviewedUserId = reviewedUserId;
    }
}
