package com.webshop.controller;

import com.webshop.dto.CreateReviewDto;
import com.webshop.dto.ReviewDto;
import com.webshop.exception.AccountNotFoundException;
import com.webshop.exception.ReviewAndReviewedException;
import com.webshop.model.Account;
import com.webshop.model.Review;
import com.webshop.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create/{reviewedUserId}")
    public ResponseEntity<String> createReview(@PathVariable("reviewedUserId") Long reviewedUserId, @RequestBody CreateReviewDto createReviewDto, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>("Not logged in", HttpStatus.UNAUTHORIZED);
        }
        Long reviewerId = account.getId();
        try {
            reviewService.createReview(reviewerId, createReviewDto,reviewedUserId);
            return ResponseEntity.ok("Review successfully created!");
        } catch (AccountNotFoundException | ReviewAndReviewedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/from-reviewees")
    public ResponseEntity<List<ReviewDto>> getReviewsFromReviewees(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Long accountId = account.getId();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        try{
            List<Review> reviewList = reviewService.getReviewsFromReviewees(accountId);
            for(Review review : reviewList){
                ReviewDto reviewDto = new ReviewDto(review);
                reviewDtoList.add(reviewDto);
            }
            return ResponseEntity.ok(reviewDtoList);
        } catch (AccountNotFoundException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-reviewer")
    public ResponseEntity<List<ReviewDto>> getReviewsByReviewer(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Long accountId = account.getId();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        try{
            List<Review> reviewList = reviewService.getReviewsByReviewer(accountId);
            for(Review review : reviewList){
                ReviewDto reviewDto = new ReviewDto(review);
                reviewDtoList.add(reviewDto);
            }
            return ResponseEntity.ok(reviewDtoList);
        } catch (AccountNotFoundException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
