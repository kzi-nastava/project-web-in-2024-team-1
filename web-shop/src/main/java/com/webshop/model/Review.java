package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Account user;

    public Review() {
        this(1,"", new Date(0));
    }

    public Review(int Rating, String Comment, Date ReviewDate) {
        this.Rating = Rating;
        this.Comment = Comment;
        this.ReviewDate = ReviewDate;
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

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", Rating=" + Rating +
                ", Comment='" + Comment + '\'' +
                ", ReviewDate=" + ReviewDate +
                '}';
    }
}
