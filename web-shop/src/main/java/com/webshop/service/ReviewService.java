package com.webshop.service;

import com.webshop.dto.CreateReviewDto;
import com.webshop.exception.AccountNotFoundException;
import com.webshop.exception.AccountRoleException;
import com.webshop.exception.ReviewAndReviewedException;
import com.webshop.model.Account;
import com.webshop.model.Product;
import com.webshop.model.Review;
import com.webshop.model.Role;
import com.webshop.repository.AccountRepository;
import com.webshop.repository.ProductRepository;
import com.webshop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductRepository productRepository;

    public void createReview(Long reviewerId, CreateReviewDto createReviewDto,Long reviewedUserId){
        Account reviewer = accountRepository.findById(reviewerId).orElseThrow(() -> new AccountNotFoundException("Reviewer not found"));
        Account reviewedUser = accountRepository.findById(reviewedUserId).orElseThrow(() -> new AccountNotFoundException("Reviewed user not found"));

        if(reviewedUser.getId().equals(reviewer.getId())){
            throw new ReviewAndReviewedException("Reviewer and reviewed user cannot be the same");
        }

        if(reviewer.getUserRole() == Role.CUSTOMER  && reviewedUser.getUserRole() == Role.SELLER ){
            createAndSaveReview(createReviewDto, reviewer, reviewedUser);
            double averageRating = getAverageRatingByReviewerId(reviewedUser);
            reviewedUser.setAverageRating(averageRating);
            accountRepository.save(reviewedUser);
        } else if(reviewer.getUserRole() == Role.SELLER && reviewedUser.getUserRole() == Role.CUSTOMER){
            createAndSaveReview(createReviewDto, reviewer, reviewedUser);
            double averageRating = getAverageRatingByReviewerId(reviewedUser);
            reviewedUser.setAverageRating(averageRating);
            accountRepository.save(reviewedUser);
        } else {
            throw new ReviewAndReviewedException("Invalid review roles. Customers can only review sellers and vice versa.");
        }


    }

    private void createAndSaveReview(CreateReviewDto createReviewDto, Account reviewer, Account reviewedUser){
        Review review = new Review();
        review.setRating(createReviewDto.getRating());
        if(review.getRating() < 1 || review.getRating() > 5 ){
            throw new ReviewAndReviewedException("Invalid rating");
        }
        review.setComment(createReviewDto.getComment());
        review.setReviewDate(new Date());
        review.setReviewer(reviewer);
        review.setReviewedUser(reviewedUser);

        reviewRepository.save(review);


    }

    public Double getAverageRatingByReviewerId(Account reviewedUserId){
        List<Review> reviews = reviewRepository.findByReviewedUserId(reviewedUserId.getId());
        if(reviews.isEmpty()){
            return 0.0;
        }

        double sum = 0;
        for(Review review : reviews){
            sum += review.getRating();
        }
        return sum / reviews.size();
    }

    public List<Review> getReviewsFromReviewees(Long accountId){
        return reviewRepository.findByReviewerId(accountId);
    }

    public List<Review> getReviewsByReviewer(Long accountId){
        return reviewRepository.findByReviewedUserId(accountId);
    }


    public List<Review> getMutualReviews(Long reviewerId, Long reviewedUserId) {
        List<Review> reviewsGivenByReviewer = reviewRepository.findByReviewerIdAndReviewedUserId(reviewerId, reviewedUserId);
        List<Review> reviewsGivenByReviewedUser = reviewRepository.findByReviewerIdAndReviewedUserId(reviewedUserId, reviewerId);

        if (!reviewsGivenByReviewer.isEmpty() && !reviewsGivenByReviewedUser.isEmpty()) {
            return reviewsGivenByReviewer;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Review> getAllReviews(Account currentAccount){
        if(!isAdmin(currentAccount)){
            throw new AccountRoleException("You do not have permission to view all reviews");
        }
        return reviewRepository.findAll();
    }

    public void updateReview(Long reviewId, CreateReviewDto createReviewDto, Account currentAccount){
        if (!isAdmin(currentAccount)) {
            throw new AccountRoleException("You do not have permission to update this review");
        }

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setRating(createReviewDto.getRating());
        review.setComment(createReviewDto.getComment());
        reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId,Account currentAccount){
        if (!isAdmin(currentAccount)) {
            throw new AccountRoleException("You do not have permission to delete this review");
        }
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent()){
            Review review = reviewOptional.get();
           /* if(isAdmin(currentAccount) || review.getReviewer().equals(currentAccount)){
                reviewRepository.delete(review);
            } else{
                throw new AccountRoleException("You do not have permission to delete this review");
            }*/
            reviewRepository.delete(review);
        } else{
            throw new RuntimeException("Review not found");
        }
    }

    private boolean isAdmin(Account account){
        return account != null && account.getUserRole() == Role.ADMINISTRATOR;
    }



}
