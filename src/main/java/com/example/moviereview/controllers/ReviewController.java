package com.example.moviereview.controllers;

import com.example.moviereview.dtos.ReviewDTO;
import com.example.moviereview.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("add_review")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO.Create reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }
}
