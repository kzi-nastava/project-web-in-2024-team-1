package com.webshop.repository;

import com.webshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewerId(Long reviewerId);
    List<Review> findByReviewedUserId( Long reviewedUserId);

    /*@Query("SELECT r FROM Review r WHERE r.reviewer.id = :accountId")
    List<Review> findReviewsByReviewer(@Param("accountId") Long accountId);
    @Query("SELECT r FROM Review r WHERE r.reviewedUser.id = :accountId")
    List<Review> findReviewsFromReviewees(@Param("accountId") Long accountId);*/
}
