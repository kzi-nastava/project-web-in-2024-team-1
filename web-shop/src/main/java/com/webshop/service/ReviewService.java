package com.webshop.service;

import com.webshop.dto.CreateReviewDto;
import com.webshop.exception.AccountNotFoundException;
import com.webshop.exception.ReviewAndReviewedException;
import com.webshop.model.Account;
import com.webshop.model.Review;
import com.webshop.repository.AccountRepository;
import com.webshop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountRepository accountRepository;

    public void createReview(Long reviewerId, CreateReviewDto createReviewDto,Long reviewedUserId){
        Account reviewer = accountRepository.findById(reviewerId).orElseThrow(() -> new AccountNotFoundException("Reviewer not found"));
        Account reviewedUser = accountRepository.findById(reviewedUserId).orElseThrow(() -> new AccountNotFoundException("Reviewed user not found"));

        if(reviewedUser.getId().equals(reviewer.getId())){
            throw new ReviewAndReviewedException("Reviewer and reviewed user cannot be the same");
        }

        Review review = new Review();
        review.setRating(createReviewDto.getRating());
        review.setComment(createReviewDto.getComment());
        review.setReviewDate(new Date());
        review.setReviewer(reviewer);
        review.setReviewedUser(reviewedUser);

        reviewRepository.save(review);
        /*reviewedUser.getReviewList().add(review);
        accountRepository.save(reviewedUser);*/
    }

    public List<Review> getReviewsFromReviewees(Long accountId){
        return reviewRepository.findByReviewerId(accountId);
    }

    public List<Review> getReviewsByReviewer(Long accountId){
        return reviewRepository.findByReviewedUserId(accountId);
    }

}
