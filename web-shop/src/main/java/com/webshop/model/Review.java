package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int Rating;

    @Column
    private String Comment;

    @Column
    private Date ReviewDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    public Review() {
        this(1L,1,"",new Date(0),new Account());
    }

    public Review(Long id, int Rating, String Comment, Date ReviewDate, Account user) {
        this.id = id;
        this.Rating = Rating;
        this.Comment = Comment;
        this.ReviewDate = ReviewDate;
        this.user = user;
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

    public Account getReviewer() {
        return user;
    }

    public void setReviewer(Account reviewer) {
        user = reviewer;
    }
}
