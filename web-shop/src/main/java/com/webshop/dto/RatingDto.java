package com.webshop.dto;

import com.webshop.model.Account;

public class RatingDto {

    private Double mark;
    private String comment;
    private Double averageRating;

    public RatingDto() {  }

    public RatingDto(Double mark, String comment, Double averageRating) {
        this.mark = mark;
        this.comment = comment;
        this.averageRating = averageRating;
    }

    public RatingDto(Account account) {
        //this.mark = 0;
        //this.comment = "";
        this.averageRating = account.getAverageRating();
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
