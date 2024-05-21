package com.webshop.service;

import com.webshop.dto.CreateReviewDto;
import com.webshop.exception.AccountNotFoundException;
import com.webshop.exception.ReviewAndReviewedException;
import com.webshop.model.Account;
import com.webshop.model.Review;
import com.webshop.model.Role;
import com.webshop.repository.AccountRepository;
import com.webshop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if(reviewer.getUserRole() == Role.CUSTOMER && reviewedUser.getUserRole() == Role.SELLER){
            createAndSaveReview(createReviewDto, reviewedUser, reviewer);
        } else if(reviewer.getUserRole() == Role.SELLER && reviewedUser.getUserRole() == Role.CUSTOMER){
            createAndSaveReview(createReviewDto, reviewedUser, reviewer);
        } else {
            throw new ReviewAndReviewedException("Invalid review roles. Customers can only review sellers and vice versa.");
        }


    }

    private void createAndSaveReview(CreateReviewDto createReviewDto, Account reviewer, Account reviewedUser){
        Review review = new Review();
        review.setRating(createReviewDto.getRating());
        review.setComment(createReviewDto.getComment());
        review.setReviewDate(new Date());
        review.setReviewer(reviewer);
        review.setReviewedUser(reviewedUser);

        reviewRepository.save(review);
    }

    public List<Review> getReviewsFromReviewees(Long accountId){
        return reviewRepository.findByReviewerId(accountId);
    }

    public List<Review> getReviewsByReviewer(Long accountId){
        return reviewRepository.findByReviewedUserId(accountId);
    }

    /*public List<Review> getMutualReviews(Long reviewerId) {
        List<Review> mutualReviews = new ArrayList<>();
        List<Review> reviewsGivenByReviewer = reviewRepository.findByReviewerId(reviewerId);
        for(Review review : reviewsGivenByReviewer){
            List<Review> reviewsFromReviewedUser = reviewRepository.findByReviewedUserId(review.getReviewedUser().getId());
            for(Review r : reviewsFromReviewedUser){
                if(r.getReviewedUser().getId().equals(reviewerId)){
                    mutualReviews.add(review);
                    break;
                }
            }
        }
        return  mutualReviews;
    }*/

    public List<Review> getMutualReviews(Long reviewerId, Long reviewedUserId) {
        List<Review> reviewsGivenByReviewer = reviewRepository.findByReviewerIdAndReviewedUserId(reviewerId, reviewedUserId);
        List<Review> reviewsGivenByReviewedUser = reviewRepository.findByReviewerIdAndReviewedUserId(reviewedUserId, reviewerId);

        if (!reviewsGivenByReviewer.isEmpty() && !reviewsGivenByReviewedUser.isEmpty()) {
            return reviewsGivenByReviewer;
        } else {
            return new ArrayList<>();
        }
    }

    /*public boolean chekIfMutual(Long reviewerId, Long reviewedUserId){
        List<Review> reviewsGivenByReviewer = reviewRepository.findByReviewerId(reviewerId);
        for(Review review : reviewsGivenByReviewer){
            if(review.getReviewedUser().getId().equals(reviewedUserId)){
                return true;
            }
        }
        return false;
    }*/

}
